package controller.binding;

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

@WebServlet(name="unbinding", urlPatterns={"/unbinding"})
public class UnBindingServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
		String id = request.getParameter("id");
        System.out.println(id);
		Boolean flag = false;
		String msg = "解绑失败，请重试！";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
                            "root", "980908");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM account_info WHERE id = ?");
            statement.setString(1, id);
            int count = statement.executeUpdate();
            System.out.println(count + "   " + id);
            if (count == 1) {
                flag = true;
                msg = "解绑成功！";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

		session.setAttribute("flag", flag);
		session.setAttribute("msg", msg);
        System.out.println(flag + msg);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/unbinding.jsp");
		rd.forward(request, response);

	}
}