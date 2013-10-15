package com.in6k.twitter.servlet;

import com.in6k.twitter.db.management.AccountDAO;
import com.in6k.twitter.ConsoleMessagePrinter;
import com.in6k.twitter.db.management.MessageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (AccountDAO.isValid(login, password)) {
            request.getSession().setAttribute("authorized", true);
            request.getSession().setAttribute("login", login);
            response.sendRedirect("/home/" + login);
            return;
        }

        request.getRequestDispatcher("WEB-INF/pages/login-form-error.jsp").include(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").include(request, response);
    }
}
