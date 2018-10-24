package controller.mail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name="receiveMail", urlPatterns={"/receiveMail"})
public class ReceiveMailServlet extends HttpServlet {
	public void service(HttpServletRequest request,
						HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/receiveMail.jsp");
		rd.forward(request, response);
	}
}
