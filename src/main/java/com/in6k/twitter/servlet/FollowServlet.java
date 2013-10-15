package com.in6k.twitter.servlet;

import com.in6k.twitter.db.management.AccountDAO;
import com.in6k.twitter.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FollowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String followerName = (String) request.getSession().getAttribute("login");
        String url = request.getRequestURI();
        String followedName = (String) url.substring(url.lastIndexOf("/") +1);

        User follower = null;
        User followed = null;
        follower = AccountDAO.getUser(followerName);
        followed = AccountDAO.getUser(followedName);

        try {
            AccountDAO.follow(follower.getId(), followed.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/home/" + followedName);
    }
}
