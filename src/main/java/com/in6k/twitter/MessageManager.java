package com.in6k.twitter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    public List<String> getMessage(String message) throws SQLException, ClassNotFoundException {
            List<String> result = new ArrayList<String>();

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter", "root", "masterkey");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT message FROM twits");

            while (rs.next()) {
                String value = rs.getString("message");
                result.add(value);
            }

            rs.close();
            s.close();
            c.close();
            return result;

    }
}