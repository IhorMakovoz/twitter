package com.in6k.twitter.dataBaseMenegment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusManager {
    public static void addFolowStatus(String login, String password) {
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
}
