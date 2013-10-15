package com.in6k.twitter.db.management;

import com.in6k.twitter.model.Tweet;
import com.in6k.twitter.model.User;

import java.sql.*;
import java.util.*;

public class MessageDAO {
    public static List<String> getMessage() throws SQLException, ClassNotFoundException {
        List<String> message = new ArrayList<String>();
        try {
        Connection c = DatabaseConnectionHelper.getConnection();

        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT message FROM twits");

        while (rs.next()) {
            String value = rs.getString("message");
            message.add(value);
        }

        rs.close();
        s.close();

        }
        catch (Exception e) {
            return null;
        }

        return message;
    }

    public static void addMessage(Integer userId, String message) {
        try {
            Connection c = DatabaseConnectionHelper.getConnection();

            PreparedStatement ps = c.prepareStatement("INSERT INTO twits " + "(user_id, date_at, message) VALUES" + "(?, ?, ?)");

            ps.setInt(1, userId);
            ps.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            ps.setString(3, message);

            ps.executeUpdate();

            ps.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Tweet> getLastMessages(Integer userId) {

        List<Tweet> result = new ArrayList<Tweet>();
        try{
            Connection c = DatabaseConnectionHelper.getConnection();

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM twits " +
                "WHERE user_id IN (SELECT friend_id FROM friends WHERE USER_ID=" + userId + ") OR user_id=" + userId +
                " ORDER BY date_at DESC LIMIT 20");


            while (rs.next()) {
                Tweet tweet = new Tweet();
                User user = AccountDAO.getUserById(Integer.parseInt(rs.getString("user_id")));

                tweet.setMessage(rs.getString("message"));
                tweet.setDateAt(Timestamp.valueOf(rs.getString("date_at")));
                tweet.setAuthor(user);
                result.add(tweet);
            }

            rs.close();
            s.close();

        }
        catch (Exception e) {
            return null;
        }
        return result;
    }
}