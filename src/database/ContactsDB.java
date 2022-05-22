package database;

import helper.JDBC;
import helper.ListManager;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for contact database methods
 */
public class ContactsDB {

    /**
     * Method used to retrieve contact ID and contact name from contacts SQL table
     */
    public static void select() {
        String sqlStatement = "SELECT * FROM contacts";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int contactId = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                ListManager.allContacts.add((new Contacts(contactId, contactName)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
