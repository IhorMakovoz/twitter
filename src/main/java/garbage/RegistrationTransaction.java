package garbage;

import com.in6k.twitter.dataBaseMenegment.DataBaseConnectionHelper;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationTransaction {
   //private List<Registration> registrations = new ArrayList<Registration>();

    public void addRegistration(Registration registration) throws SQLException, ClassNotFoundException {
       // List<Registration> registrations = new ArrayList<Registration>();
        Connection connection = DataBaseConnectionHelper.getConnection();

        String insertTableSQL = "INSERT INTO users"
                              + "(login, password) VALUES"
                              + "(?,?)";

        PreparedStatement statement = connection.prepareStatement("INSERT INTO users"
                                                                + "(login, password) VALUES"
                                                                + "(?,?)");

        if (registration.getLogin().equals(registration.getPassword())){
            statement.close();
        }
        else {
            statement.setString(1, registration.getLogin());
            statement.setString(2, registration.getPassword());

            statement.executeUpdate();
            statement.close();
        }


    }

    /*public List<Registration> getRegistrationLogin() throws SQLException, ClassNotFoundException {
        List<Registration> result = new ArrayList<Registration>();

        Connection connection = DataBaseConnectionHelper.getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT login, password FROM users");

        while (rs.next()){
            result.add(createRegistration(rs));
        }
        rs.close();
        statement.close();

        return result;
    }*/

    public static boolean getRegistrationLogin(Registration registration) throws SQLException, ClassNotFoundException {
        List<Registration> result = new ArrayList<Registration>();

        Connection connection = DataBaseConnectionHelper.getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT login, password FROM users");

        while (rs.next()){
            result.add(createRegistration(rs));
        }
        rs.close();
        statement.close();

        for (Registration log : result) {
            if (log.equals(registration));
                return false;
        }

        return true;
    }


    private static Registration createRegistration(ResultSet rs) throws SQLException {
        return new Registration(rs.getString("login"), rs.getString("password"));
    }

}
