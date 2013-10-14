package com.in6k.twitter.dataBaseMenegment;

import com.in6k.twitter.primaryClasses.Tweet;
import com.in6k.twitter.primaryClasses.User;

import java.sql.*;
import java.util.*;

public class MessageManager {
    public static List<String> getMessage() throws SQLException, ClassNotFoundException {
        List<String> message = new ArrayList<String>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT message FROM twits");

        while (rs.next()) {
            String value = rs.getString("message");
            message.add(value);
        }

        rs.close();
        s.close();
        //c.close();
        return message;
    }

    public static void addMessage(Integer userId, String message) {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

            PreparedStatement ps = c.prepareStatement("INSERT INTO twits " + "(user_id, date_at, message) VALUES" + "(?, ?, ?)");

            ps.setInt(1, userId);
            ps.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            ps.setString(3, message);

            ps.executeUpdate();

            ps.close();
            c.close();

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Tweet> getLastMessages(Integer userId) throws ClassNotFoundException, SQLException {

        List<Tweet> messages = new ArrayList<Tweet>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM twits " +
                "where user_id " +
                "in(SELECT friend_id FROM friends WHERE USER_ID=" + userId + ") " +
                "ORDER BY date_at " +
                "DESC " +
                "LIMIT 20");


        while (rs.next()) {
            Tweet tweet = new Tweet();
            User user = AccountManager.getUserById(Integer.parseInt(rs.getString("user_id")));

            tweet.setMessage(rs.getString("message"));
            tweet.setDateAt(Timestamp.valueOf(rs.getString("date_at")));
            tweet.setAuthor(user);
            messages.add(tweet);
        }

        rs.close();
        s.close();

        return messages;
    }
}