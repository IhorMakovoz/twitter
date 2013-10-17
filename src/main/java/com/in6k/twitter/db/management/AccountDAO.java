package com.in6k.twitter.db.management;

import com.in6k.twitter.domain.Friend;
import com.in6k.twitter.domain.User;
import com.in6k.twitter.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Query;

import java.sql.*;
import java.util.List;

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
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("FROM User u WHERE u.login= :login");

            query.setParameter("login", login);
            List<User> users = query.list();

            result = users.get(0);


        }finally {
            session.close();
            return result;
        }
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
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        User user = (User) session.get(User.class, new Integer(follower) );
        User userFriend = (User) session.get(User.class, new Integer(followed));
        Friend friend = new Friend();

        friend.setUser(user);
        friend.setFriend(userFriend);

        session.save(friend);

        session.getTransaction().commit();


    }
    public static void unfollow(Integer follower, Integer followed) {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        User user = (User) session.get(User.class, new Integer(follower) );
        User userFriend = (User) session.get(User.class, new Integer(followed));

        Query query = session.createQuery("DELETE Friend f WHERE f.user= :user AND f.friend= :friend");

        query.setParameter("user", user);
        query.setParameter("friend", userFriend);
        query.executeUpdate();

        session.getTransaction().commit();
    }

    public static Boolean isFollowedByUser(Integer follower, Integer followed) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            User user = (User) session.get(User.class, new Integer(follower));
            User userFriend = (User) session.get(User.class, new Integer(followed));

            Query query = session.createQuery("FROM Friend f WHERE f.user= :user AND f.friend= :friend");
            query.setParameter("user", user);
            query.setParameter("friend", userFriend);

            if (query.list().size() > 0) {
                return true;
            }
        }
        finally {
            session.close();
        }
        return false;
    }



    /*public static Boolean isFollowedByUser(String follower, String followed) {
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
    }*/
}