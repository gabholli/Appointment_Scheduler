package helper;

import java.sql.*;

/**
 * Class used to handle connection to database
 */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface
    private static final String selectQuery = "SELECT * FROM users WHERE User_Name = ? and Password = ?";

    /**
     * Method used to establish program connection to database
     * @return Returns null if connection is not made
     */
    public static Connection openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used to validate username and password input in login screen
     * @param userId Parameter used to access typed-in username
     * @param password1 Parameter used to access typed-in password
     * @return Method returns true if typed in values match database entries, false if not
     */
    public boolean validation(String userId, String password1) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, password)) {

            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ps.setString(1, userId);
            ps.setString(2, password1);

            System.out.println(ps);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ignored) {

        }
        return false;
    }

    /**
     * Method used to close connection to database with application
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            //System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

}
