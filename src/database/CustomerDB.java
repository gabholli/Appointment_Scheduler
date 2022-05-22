package database;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class containing customer database methods
 */
public class CustomerDB {

    /**
     * Method used to populate customer observable list
     * @return Return value for populated customer observable list
     * @throws Exception For throwing Exception
     */
    public static ObservableList<Customer> getAllCustomers() throws Exception {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM customers INNER JOIN countries INNER JOIN first_level_divisions ON countries.Country_ID = " +
                "first_level_divisions.Country_ID WHERE customers.Division_ID = first_level_divisions.Division_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            int customerId = result.getInt("Customer_ID");
            String customerNameG = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phoneNumber = result.getString("Phone");
            String countryName = result.getString("Country");
            String divisionName = result.getString("Division");
            int countryId = result.getInt("Country_ID");
            int divisionId = result.getInt("Division_ID");
            Customer customerResult = new Customer(customerId, customerNameG, address, postalCode, phoneNumber,
                    countryName, divisionName, countryId, divisionId);
            allCustomers.add(customerResult);
        }
        return allCustomers;
    }

    /**
     * Method used for updating customer records in application and customer database table
     * @param name Parameter used for accessing customer name from update records form
     * @param address Parameter used for accessing customer address from update records form
     * @param postalCode Parameter used for accessing customer postal code from update records form
     * @param phone Parameter used for accessing customer phone number from update records form
     * @param divisionId Parameter used for accessing division ID from update records form
     * @param customerId Parameter used for accessing customer ID from update records form
     * @throws SQLException For throwing SQLException
     */
    public static void updateRecord(String name, String address, String postalCode, String phone,
                                    int divisionId, int customerId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        ps.executeUpdate();
    }

    /**
     * Method used for adding customer records to application and customer database table
     * @param name Parameter used for accessing customer name from add records form
     * @param address Parameter used for accessing customer address from add records form
     * @param postalCode Parameter used for accessing customer postal code from add records form
     * @param phone Parameter used for accessing customer phone number from add records form
     * @param divisionId Parameter used for accessing division ID from add records form
     * @throws SQLException For throwing SQLException
     */
    public static void addRecord(String name, String address, String postalCode, String phone,
                                 int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.executeUpdate();
    }

    /**
     * Method used for deleting customer records in application and from corresponding database table
     * @param customer Parameter used for selection of customer for deletion
     * @param tableView Parameter used to access customer record table from main screen
     * @throws SQLException For throwing SQLException
     */
    public static void deleteRecord(Customer customer, TableView<Customer> tableView) throws SQLException {
        int getId = customer.getCustomerId();

        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, getId);
        ps.executeUpdate();
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Method used for third report in reports screen, to generate total number of unique customers
     * @param label Parameter used for using label in reports screen to display total number of unique customers
     * @throws SQLException For throwing SQLException
     */
    public static void totalNumberOfCustomers(Label label) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT Customer_Name) AS Customer_Name FROM customers";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String customerName = rs.getString("Customer_Name");
            label.setText(String.valueOf(customerName));
        }
    }
}