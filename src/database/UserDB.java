package database;

import helper.JDBC;
import helper.ListManager;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class containing methods used to access user database table
 */
public class UserDB {

    /**
     * Method used to retrieve user ID from user database table
     */
    public static void select() {
        String sqlStatement = "SELECT * FROM users";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int userId = result.getInt("User_ID");
                ListManager.allUsers.add(new User(userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
