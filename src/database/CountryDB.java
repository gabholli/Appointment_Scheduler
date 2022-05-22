package database;

import helper.JDBC;
import helper.ListManager;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used for country database methods
 */
public class CountryDB {

    /**
     * Method used to retrieve country name and country ID from country SQL table
     */
    public static void select() {
        String sqlStatement = "SELECT * FROM countries";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String countryName = result.getString("Country");
                int id = result.getInt("Country_ID");
                ListManager.allCountries.add(new Country(id, countryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


