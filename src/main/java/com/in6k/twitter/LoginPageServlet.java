package com.in6k.twitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (AccountManager.isValid(login, password)) {
            request.getSession().setAttribute("authorized", true);
            request.getSession().setAttribute("login", login);
            request.getRequestDispatcher("/home-page.jsp").include(request, response);
            return;
        }

        request.getRequestDispatcher("/login.jsp").include(request, response);
    }
}
