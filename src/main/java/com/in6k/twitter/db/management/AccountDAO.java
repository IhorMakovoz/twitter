package com.in6k.twitter.db.management;

import com.in6k.twitter.domain.User;
import com.in6k.twitter.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDAO {

    public static boolean isUnique (String  login) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            List<User> result = session.createQuery("FROM User ").list();

            session.getTransaction().commit();

            for(User user : result) {
                if (login.equals(user.getLogin())) {
                    return false;
                }
            }
        }
        finally {
            session.close();
        }
        return true;
    }

    public static boolean isValid(String login, String password) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            List<User> result = session.createQuery("FROM User ").list();

            session.getTransaction().commit();

            for(User user : result) {
                if (login.equals(user.getLogin()) && (password.equals(user.getPassword()))) {
                    return true;
                }
            }
        }
        finally {
            session.close();
        }
        return false;
    }

    public static void addAccount(String login, String password) {
        Session session = HibernateUtil.getSession();

        User user = new User();

        user.setLogin(login);
        user.setPassword(password);

        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static User getUser(String login) {
        User result = new User();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM users WHERE login='" + login + "'");

            try {
                while (rs.next()) {
                    result.setId(Integer.parseInt(rs.getString("id")));
                    result.setLogin(login);
                }
            }
            finally {
                rs.close();
                s.close();
            }

        } catch (Exception e) {
            return null;
        }

        return result;
    }

    public static User getUserById(Integer id) {
        User result = new User();

        try {
        Connection c = DatabaseConnectionHelper.getConnection();

        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM users WHERE id='" + id + "'");

        while (rs.next()) {
            result.setId(id);
            result.setLogin(rs.getString("login"));
        }

        rs.close();
        s.close();

        } catch (Exception e) {
            return null;
        }

        return result;
    }

    public static void follow(Integer follower, Integer followed) {
        try {
        Connection c = DatabaseConnectionHelper.getConnection();

        PreparedStatement ps = c.prepareStatement("INSERT INTO friends (user_id, friend_id) VALUES(?, ?)");

        try {
            ps.setInt(1, follower);
            ps.setInt(2, followed);
            ps.executeUpdate();
        }
        finally {
            ps.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unfollow(Integer follower, Integer followed) {
        try {
        Connection c = DatabaseConnectionHelper.getConnection();

        PreparedStatement ps = c.prepareStatement("DELETE FROM friends where user_id=? AND friend_id=?");

        try {
            ps.setInt(1, follower);
            ps.setInt(2, followed);
            ps.executeUpdate();
        }
        finally {
            ps.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Boolean isFollowedByUser(String follower, String followed) {
        try {
        Connection c = DatabaseConnectionHelper.getConnection();

        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT id FROM friends WHERE user_id=" + getUser(follower).getId()
            + " AND friend_id=" + getUser(followed).getId());

        try {
            if (rs.next()) return true;
        }
        finally {
            rs.close();
            s.close();
        }

        } catch (Exception e) {
            return null;
        }

        return false;
    }
}