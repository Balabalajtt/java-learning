package controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name="logout", urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet {
	public void service(HttpServletRequest request,
						HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
}
