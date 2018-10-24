package controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

import java.sql.*;
@WebServlet(name="register", urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String errMsg = "";
        RequestDispatcher rd;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                            "root", "980908");

            PreparedStatement statement =
                    connection.prepareStatement("select username from user_info where username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();


            if (rs.next()) {
                errMsg += "用户名已存在";
            } else if (password.equals("")) {
                errMsg += "密码不能为空";
            } else if (!password.equals(confirm)) {
                errMsg += "两次输入的密码不一致";
            } else {
                statement = connection.prepareStatement("insert into user_info (username, password)values(?,?)");
                statement.setString(1, username);
                statement.setString(2, password);
                int count = statement.executeUpdate();
                if (count == 1) {
                    rd = request.getRequestDispatcher("/prompt.jsp");
                    rd.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (errMsg != null && !errMsg.equals("")) {
            rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
            request.setAttribute("err", errMsg);
            rd.forward(request, response);
        }
    }
}
