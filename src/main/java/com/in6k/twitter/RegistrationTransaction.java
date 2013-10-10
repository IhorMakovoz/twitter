package com.in6k.twitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationTransaction {
//    private List<Registration> registrations = new ArrayList<Registration>();

    public void addRegistration(Registration registration) throws SQLException, ClassNotFoundException {
        List<Registration> registrations = new ArrayList<Registration>();
        Connection connection = DataBaseConnectionHelper.getConnection();

        String insertTableSQL = "INSERT INTO users"
                              + "(login, password) VALUES"
                              + "(?,?)";

        PreparedStatement statement = connection.prepareStatement("INSERT INTO users"
                                                                + "(login, password) VALUES"
                                                                + "(?,?)");

        statement.setString(1, registration.getLogin());
        statement.setString(2, registration.getPassword());
        statement.executeUpdate();

        statement.close();
    }
}
