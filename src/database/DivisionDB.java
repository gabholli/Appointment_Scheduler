package database;

import helper.JDBC;
import helper.ListManager;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class containing first level division database methods
 */
public class DivisionDB {

    /**
     * Method used for retrieving division name, division ID, and country ID from
     * first level divisions table in database
     */
    public static void select() {
        String sqlStatement = "SELECT * FROM first_level_divisions";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String division = result.getString("Division");
                int id = result.getInt("Division_ID");
                int countryId = result.getInt("Country_ID");
                ListManager.allDivisions.add(new Division(id, division, countryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
