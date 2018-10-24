package controller.mail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name="mailDetail", urlPatterns={"/mailDetail"})
public class MailDetailServlet extends HttpServlet {
	public void service(HttpServletRequest request,
						HttpServletResponse response)
			throws ServletException, java.io.IOException {
		HttpSession session = request.getSession(false);
		String id = request.getParameter("id");
		session.setAttribute("id", id);

		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/mail.jsp");
		rd.forward(request, response);
	}
}
