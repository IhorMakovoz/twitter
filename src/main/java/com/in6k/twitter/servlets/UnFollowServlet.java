package com.in6k.twitter.servlets;

import com.in6k.twitter.dataBaseMenegment.AccountManager;
import com.in6k.twitter.primaryClasses.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UnFollowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String followerName = (String) request.getSession().getAttribute("login");
        String followedName = (String) request.getParameter("user");

        User follower = null;
        User followed = null;
        try {
            follower = AccountManager.getUser(followerName);
            followed = AccountManager.getUser(followedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            AccountManager.unfollow(follower.getId(), followed.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/home");
    }
}
