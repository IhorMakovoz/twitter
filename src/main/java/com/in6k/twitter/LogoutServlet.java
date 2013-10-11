package com.in6k.twitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getSession().setAttribute("authorized", false);
            request.getSession().setAttribute("login", null);
            request.getRequestDispatcher("WEB-INF/pages/home-page.jsp").include(request, response);

    }
}