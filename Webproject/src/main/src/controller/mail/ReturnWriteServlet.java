package controller.mail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name="again", urlPatterns={"/again"})
public class ReturnWriteServlet extends HttpServlet {
	public void service(HttpServletRequest request,
						HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/writeMail.jsp");
		rd.forward(request, response);
	}
}
