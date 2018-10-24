package controller.binding;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@WebServlet(name="tobinding", urlPatterns={"/tobinding"})
public class ToBindingServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account + "*********************************" + password);

		Boolean flag = false;
		String msg = "";

		// 尝试链接邮箱，看账号密码是否正确
		if (account != null && !account.equals("") && password != null && !password.equals("")) {
			try {
				Properties prop = new Properties();
				prop.setProperty("mail.transport.protocol", "smtp"); //协议
				prop.setProperty("mail.smtp.host", "smtp.163.com"); //主机名
				prop.setProperty("mail.smtp.auth", "true"); //是否开启权限控制
				prop.setProperty("mail.debug", "true"); //返回发送的cmd源码
				Session instance = Session.getInstance(prop);
				Transport trans = instance.getTransport();
				trans.connect(account, password); // 邮件的账号密码
				flag = true;
			} catch (MessagingException e) {
				e.printStackTrace();
				flag = false;
				msg = "邮箱账号或密码错误或邮箱POP3未设置！";
			}
		}

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                            "root", "980908");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account_info WHERE mail_account = ?");
            statement.setString(1, account);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                flag = false;
                msg = "该邮箱已被绑定！";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                                "root", "980908");
                PreparedStatement statement = connection.prepareStatement("insert into account_info (username, mail_account, mail_password) values (?,?,?)");
                statement.setString(1, String.valueOf(session.getAttribute("username")));
                statement.setString(2, account);
                statement.setString(3, password);
                int count = statement.executeUpdate();
                if (count != 1) {
                    flag = false;
                    msg = "邮箱绑定失败，请重试！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


		session.setAttribute("flag", flag);
		session.setAttribute("msg", msg);
        System.out.println(flag + msg);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/tobinding.jsp");
		rd.forward(request, response);

	}
}