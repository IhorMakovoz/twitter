package com.in6k.twitter.db.management;

import com.in6k.twitter.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDAO {


    public static boolean isUnique(String login) {
        try {
            List<String> result = new ArrayList<String>();

            Connection c = DatabaseConnectionHelper.getConnection();

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

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean isValid(String login, String password) {
        try {
            Map<String, String> result = new HashMap<String, String>();

            Connection c = DatabaseConnectionHelper.getConnection();

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT login, password FROM users");

            while (rs.next()) {
                result.put(rs.getString("login"), rs.getString("password"));
            }

            rs.close();
            s.close();

            for(Map.Entry<String, String> entry : result.entrySet()) {
                if(login.equals(entry.getKey())) {
                    if(password.equals(entry.getValue())) {
                        return true;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void addAccount(String login, String password) {
        try {

            Connection c = DatabaseConnectionHelper.getConnection();

            PreparedStatement ps = c.prepareStatement("INSERT INTO users (login, password) VALUES (?, ?)");

            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeUpdate();

            ps.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

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