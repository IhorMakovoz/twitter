package com.in6k.twitter.servlet;

import com.in6k.twitter.db.management.AccountDAO;
import com.in6k.twitter.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        //User user = new User(login, password);

        if (AccountDAO.isUnique(login)) {
            request.getSession().setAttribute("authorized", true);
            AccountDAO.addAccount(login, password);
            request.getRequestDispatcher("/WEB-INF/pages/registration-form-success.jsp").include(request, response);
            return;
        }

        request.getRequestDispatcher("//WEB-INF/pages/registration-form-error.jsp").include(request, response);
    }
}
