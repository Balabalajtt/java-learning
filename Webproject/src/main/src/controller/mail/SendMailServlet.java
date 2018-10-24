package controller.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@WebServlet(name="send", urlPatterns={"/send"})
public class SendMailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String msg = "发送失败";
        Boolean flag = false;
        RequestDispatcher rd;
        HttpSession session = request.getSession(false);
        String pwd = "";
        String to = request.getParameter("receiver");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String from = request.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                            "root", "980908");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account_info WHERE mail_account = ?");
            statement.setString(1, from);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                pwd = rs.getString("mail_password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(pwd + "********" + from);

        boolean isSucc = sendMail(from, pwd, to, title, content);

        if (isSucc) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                                "root", "980908");
                System.out.println("jdbc " + from + pwd + to + title + content);
                PreparedStatement statement = connection.prepareStatement("insert into mail_info (mail_from, mail_to, title, content, mail_date) values (?,?,?,?,now())");
                statement.setString(1, from);
                statement.setString(2, to);
                statement.setString(3, title);
                statement.setString(4, content);
                int count = statement.executeUpdate();

                if (count == 1) {
                    flag = true;
                    msg = "发送成功！";
                }




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("flag", flag);
        session.setAttribute("msg", msg);
        getServletContext().getRequestDispatcher("/sendResult.jsp").forward(request, response);
    }

    private boolean sendMail(String from, String pwd, String to, String title, String content) {


        System.out.println(from + pwd + to + title + content);
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.transport.protocol", "smtp"); //协议
            prop.setProperty("mail.smtp.host", "smtp.163.com"); //主机名
            prop.setProperty("mail.smtp.auth", "true"); //是否开启权限控制
            prop.setProperty("mail.debug", "true"); //返回发送的cmd源码
            Session session = Session.getInstance(prop);
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(from)); //自己的email
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 要发送的email，可以设置数组
            msg.setSubject(title); //邮件标题
            msg.setText(content);//邮件正文
            //不被当作垃圾邮件的关键代码--Begin ，如果不加这些代码，发送的邮件会自动进入对方的垃圾邮件列表
            msg.addHeader("X-Priority", "3");
            msg.addHeader("X-MSMail-Priority", "Normal");
            msg.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869"); //本文以outlook名义发送邮件，不会被当作垃圾邮件
            msg.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
            msg.addHeader("ReturnReceipt", "1");
            //不被当作垃圾邮件的关键代码--end
            Transport trans = session.getTransport();
            System.out.println(from + "*********" + pwd);
            trans.connect(from, pwd); // 邮件的账号密码
            trans.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}