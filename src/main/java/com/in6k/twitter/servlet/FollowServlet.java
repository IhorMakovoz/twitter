package com.in6k.twitter.servlet;

import com.in6k.twitter.db.management.AccountDAO;
import com.in6k.twitter.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        if (AccountDAO.isFollowedByUser(follower.getId(), followed.getId())) {
            AccountDAO.unfollow(follower.getId(), followed.getId());
        }
        else {
            AccountDAO.follow(follower.getId(), followed.getId());
        }
        response.sendRedirect("/home/" + followedName);
    }
}
