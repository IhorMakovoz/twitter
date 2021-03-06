package com.in6k.twitter.servlets;

import com.in6k.twitter.dataBaseMenegment.AccountManager;
import com.in6k.twitter.ConsoleMessagePrinter;
import com.in6k.twitter.dataBaseMenegment.MessageManager;

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
//            request.getRequestDispatcher("home-page.jsp").include(request, response);
            response.sendRedirect("/home");
            return;
        }

        try {
            ConsoleMessagePrinter.print(MessageManager.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/pages/login-form-error.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").include(request, response);
    }
}
