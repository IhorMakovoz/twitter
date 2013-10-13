package com.in6k.twitter.servlets;

import com.in6k.twitter.dataBaseMenegment.AccountManager;
import com.in6k.twitter.dataBaseMenegment.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String message = request.getParameter("message");
        MessageManager.addMessage(userId, message);
        request.getRequestDispatcher("home-page.jsp").include(request, response);*/

        String login = request.getSession().getAttribute("login").toString();

        String message = request.getParameter("message");
        Integer userId = null;

        try {
            userId = AccountManager.getUser(login).getId();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MessageManager.addMessage(userId, message);

        //request.getRequestDispatcher("home-page.jsp").include(request, response);
        response.sendRedirect("/home");
    }
}
