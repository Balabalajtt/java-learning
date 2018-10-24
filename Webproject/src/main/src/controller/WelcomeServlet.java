package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name="welcome", urlPatterns={"/welcome"})
public class WelcomeServlet extends HttpServlet {
	public void service(HttpServletRequest request,
						HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/welcome.jsp");
		rd.forward(request, response);

	}
}
