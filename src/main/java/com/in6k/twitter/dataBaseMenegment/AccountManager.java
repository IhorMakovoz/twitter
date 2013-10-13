package com.in6k.twitter.dataBaseMenegment;

import com.in6k.twitter.primaryClasses.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManager {


    public static boolean isUnique(String login) {
        try {
            List<String> result = new ArrayList<String>();

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT login FROM users");

            while (rs.next()) {

                String name = rs.getString("login");
                result.add(name);
            }

            for(String name : result) {
                if (name.equals(login)) {
                    return false;
                }
            }
            rs.close();
            s.close();
            c.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean isValid(String login, String password) {
        try {
            Map<String, String> result = new HashMap<String, String>();

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT login, password FROM users");

            while (rs.next()) {

                String loginvalue= rs.getString("login");
                String passwordvalue = rs.getString("password");
                result.put(loginvalue, passwordvalue);
            }

            rs.close();
            s.close();
            c.close();

            for(Map.Entry<String, String> entry : result.entrySet()) {
                if(login.equals(entry.getKey())) {
                    if(password.equals(entry.getValue())) {
                        return true;
                    }
                }
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void addAccount(String login, String password) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

            PreparedStatement ps = c.prepareStatement("INSERT INTO users " + "(login, password) VALUES" + "(?, ?)");

            ps.setString(1, login);
            ps.setString(2, password);
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

    public static User getUser(String login) throws ClassNotFoundException, SQLException {

        User user = new User();

        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM users where login='" + login + "'");

        while (rs.next()) {
            user.setId(Integer.parseInt(rs.getString("id")));
            user.setLogin(login);
        }

        rs.close();
        s.close();
        c.close();

        return user;
    }

    /*public static void follow(Integer follower, Integer followed) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

        PreparedStatement ps = c.prepareStatement("INSERT INTO friends " + "(user_id, friend_id) VALUES" + "(?, ?)");

        ps.setInt(1, follower);
        ps.setInt(2, followed);
        ps.executeUpdate();

        ps.close();
        c.close();
    }*/
}