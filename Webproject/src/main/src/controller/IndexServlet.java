package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="index", urlPatterns={"/index"})
public class IndexServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

    }

}

