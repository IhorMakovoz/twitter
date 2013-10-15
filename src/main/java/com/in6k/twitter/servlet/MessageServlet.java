package com.in6k.twitter.servlet;

import com.in6k.twitter.db.management.AccountDAO;
import com.in6k.twitter.db.management.MessageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getSession().getAttribute("login").toString();

        String message = request.getParameter("message");
        Integer userId = null;

        userId = AccountDAO.getUser(login).getId();

        MessageDAO.addMessage(userId, message);

        response.sendRedirect("/home/" + login);
    }
}
