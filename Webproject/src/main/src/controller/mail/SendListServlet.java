package controller.mail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name="sentList", urlPatterns={"/sentList"})
public class SendListServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/sendList.jsp");
		rd.forward(request, response);
	}
}