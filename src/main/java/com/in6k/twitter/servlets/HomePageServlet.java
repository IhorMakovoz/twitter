package com.in6k.twitter.servlets;

import com.in6k.twitter.primaryClasses.Tweet;
import com.in6k.twitter.primaryClasses.User;
import com.in6k.twitter.dataBaseMenegment.AccountManager;
import com.in6k.twitter.dataBaseMenegment.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String anotherUser = request.getParameter("user");

        String currentUser = (String)request.getSession().getAttribute("login");

        String login = (anotherUser == null) ? currentUser: anotherUser;

        User user = new User();

        try {
            user = AccountManager.getUser(login);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Tweet> tweets = null;

        try {
            tweets = MessageManager.getLastMessages(user.getId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean isHomepage = (currentUser != null && currentUser.equals(anotherUser)) || (anotherUser == null);

        request.setAttribute("ishomepage", isHomepage);
        request.setAttribute("currentuser", user);
        request.setAttribute("tweets", tweets);

        request.getRequestDispatcher("/home-page.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
