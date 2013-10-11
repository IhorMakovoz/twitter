package com.in6k.twitter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    /*public static void addMessage(String message) {
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
    }*/
}