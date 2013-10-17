package com.in6k.twitter.db.management;

import com.in6k.twitter.domain.Tweet;
import com.in6k.twitter.domain.User;
import com.in6k.twitter.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.*;
import java.util.*;

public class MessageDAO {

    public static void addMessage(Integer userId, String message) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class , new Integer(userId));

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setDateAt(new Timestamp(new java.util.Date().getTime()));
        tweet.setMessage(message);

        session.save(tweet);

        session.getTransaction().commit();
    }

    public static List<Tweet> getLastMessages(Integer userId) {
        List<Tweet> result = new ArrayList<Tweet>();
        Session session = HibernateUtil.getSession();
        try{
        User acc = new User(userId);


        session.beginTransaction();

        Query query = session.createQuery("FROM Tweet t WHERE t.user IN(" +
            "SELECT friend from Friend f WHERE f.user= :user) OR t.user= :user ORDER BY dateAt DESC");

        query.setParameter("user", acc);
        List<Tweet> tweets = query.list();

        session.getTransaction().commit();

        for (Tweet tweet : tweets) {
            Tweet msg = new Tweet(tweet.getUser().getLogin(), tweet.getDateAt(), tweet.getMessage());
            result.add(msg);
        }
        }finally {
            session.close();
            return result;
        }

    }
}
