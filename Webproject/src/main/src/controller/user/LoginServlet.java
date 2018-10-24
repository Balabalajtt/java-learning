package controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

import java.sql.*;
@WebServlet(name="login", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String errMsg = "";
        RequestDispatcher rd;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                            "root", "980908");

            PreparedStatement statement =
                    connection.prepareStatement("select password from user_info where username = ?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    rd = request.getRequestDispatcher("/main.jsp");
                    rd.forward(request, response);
                } else {
                    errMsg += "密码错误,请重新输入";
                }
            } else {
                errMsg += "用户名不存在，请先注册";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (errMsg != null && !errMsg.equals("")) {
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("err", errMsg);
            rd.forward(request, response);
        }
    }
}