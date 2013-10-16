package com.in6k.twitter.servlet;

import com.in6k.twitter.db.management.AccountDAO;
import com.in6k.twitter.db.management.MessageDAO;
import com.in6k.twitter.domain.Tweet;
import com.in6k.twitter.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentUser = (String)request.getSession().getAttribute("login");
        String url = request.getRequestURI();
        String anotherUser = url.substring(url.lastIndexOf("/") + 1);

        Boolean isFollowedByUser = isFollowedByUser(currentUser, anotherUser);

        String login = (anotherUser == null) ? currentUser: anotherUser;

        User user = AccountDAO.getUser(login);

        List<Tweet> tweets = null;

        tweets = MessageDAO.getLastMessages(user.getId());

        boolean isHomepage = (currentUser != null && currentUser.equals(anotherUser)) || (anotherUser == null);

        request.setAttribute("ishomepage", isHomepage);
        request.setAttribute("isfollowedbyuser", isFollowedByUser);
        request.setAttribute("currentuser", user);
        request.setAttribute("tweets", tweets);

        request.getRequestDispatcher("/home-page.jsp").include(request, response);
    }

    private boolean isFollowedByUser(String follower, String followed) {
        Boolean isFollowedByUser = false;

            if (followed != null) {
                isFollowedByUser = AccountDAO.isFollowedByUser(follower, followed);
            }

        return isFollowedByUser;
    }
}
