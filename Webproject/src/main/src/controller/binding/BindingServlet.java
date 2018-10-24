package controller.binding;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="binding", urlPatterns={"/binding"})
public class BindingServlet extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/binding.jsp");
        rd.forward(request, response);
    }
}