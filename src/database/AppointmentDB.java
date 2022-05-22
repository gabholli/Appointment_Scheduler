package database;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Appointment;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class used to contain appointment database querying methods
 */
public class AppointmentDB {

    /**
     * Method used to populate appointments observable list from database
     * @return Method returns observable list for appointments
     * @throws Exception For throwing Exception
     */
    public static ObservableList<Appointment> getAllAppointments() throws Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM appointments INNER JOIN contacts ON contacts.Contact_ID = appointments.Contact_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            Appointment appointmentResult = new Appointment(appointmentId, title, description, location, contactId,
                    contactName, type, start, end, customerId, userId);
            allAppointments.add(appointmentResult);
        }

        return allAppointments;
    }

    /**
     * Method used for updating appointments, and executing update query from database
     * @param title Parameter used to allow for title from update appointment controller to be used
     * @param description Parameter used to allow for description from update appointment controller to be used
     * @param location Parameter used to allow for location from update appointment controller to be used
     * @param type Parameter used to allow for type from update appointment controller to be used
     * @param start Parameter used to allow for appointment starting from update appointment controller to be used
     * @param end Parameter used to allow for appointment ending time from update appointment controller to be used
     * @param customerId Parameter used to allow for customer ID from update appointment controller to be used
     * @param userId Parameter used to allow for user ID from update appointment controller to be used
     * @param contactId Parameter used to allow for contact ID from update appointment controller to be used
     * @param apptId Parameter used to allow for appointment ID from update appointment controller to be used
     * @throws SQLException For throwing SQLException
     */
    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime start,
                                         LocalDateTime end, int customerId, int userId, int contactId, int apptId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, " +
                "Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, apptId);
        ps.executeUpdate();
    }

    /**
     * Method used for adding appointment and executing corresponding database query
     * @param title Parameter used to allow for title from add appointment controller to be used
     * @param description Parameter used to allow for description from add appointment controller to be used
     * @param location Parameter used to allow for location from add appointment controller to be used
     * @param type Parameter used to allow for type from add appointment controller to be used
     * @param start Parameter used to allow for appointment starting time from add appointment controller to be used
     * @param end Parameter used to allow for appointment ending time from add appointment controller to be used
     * @param customerId Parameter used to allow for customer ID from add appointment controller to be used
     * @param userId Parameter used to allow for user ID from add appointment controller to be used
     * @param contactId Parameter used to allow for contact ID from add appointment controller to be used
     * @throws SQLException For throwing SQLException
     */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start,
                                      LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.executeUpdate();
    }

    /**
     * Method used for deleting appointments from application and database
     * @param appointment Parameter used for getting selected appointment from main screen
     * @param tableView Parameter used to access selection in appointments table in main screen
     * @throws SQLException For throwing SQLException
     */
    public static void deleteAppointment(Appointment appointment, TableView<Appointment> tableView) throws SQLException {
        int getId = appointment.getApptId();

        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, getId);
        ps.executeUpdate();
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Method used for deleting corresponding appointments when customer is deleted from records table in main screen
     * @param customer Parameter used for getting selected customer from main screen
     * @param tableView Parameter used to access customer-associated appointments to delete when
     *                  customer record is deleted
     * @throws Exception For throwing Exception
     */
    public static void deleteAssociatedAppointment(Customer customer, TableView<Appointment> tableView) throws Exception {
        int getId = customer.getCustomerId();

        tableView.getSelectionModel().clearSelection();
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, getId);
        ps.executeUpdate();
        tableView.setItems(AppointmentDB.getAllAppointments());
    }

    /**
     * Method used to check for appointment time overlapping when adding an appointment
     * @param start Parameter used to access starting date and time selected in add appointment form
     * @param end Parameter used to access ending date and time selected in add appointment form
     * @param custId Parameter used to used selected customer ID in add appointment form
     * @return Returns true or false based on whether overlapping conditions met or not
     */
    public static boolean overlapAddCheck(LocalDateTime start, LocalDateTime end, int custId) {

        try {
            String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                if ((((apptStart.isAfter(start) || apptStart.isEqual(start)) && apptStart.isBefore(end))) ||
                        ((apptEnd.isAfter(start)) && (apptEnd.isBefore(end) || apptEnd.isEqual(end))) ||
                        ((apptStart.isBefore(start) || apptEnd.isEqual(start)) && (apptEnd.isAfter(end) || apptEnd.isEqual(end)))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method used to check for appointment time overlapping when updating an appointment
     * @param start Parameter used to access starting date and time selected in update appointment form
     * @param end Parameter used to access ending date and time selected in update appointment form
     * @param custId Parameter used to used selected customer ID in update appointment form
     * @param apptId Parameter used to access appointment ID from update appointment form
     * @return Returns true or false based on whether overlapping conditions are met
     */
    public static boolean overlapUpdateCheck(LocalDateTime start, LocalDateTime end, int custId, int apptId) {

        try {
            String sql = "SELECT * FROM appointments WHERE Customer_ID = ? AND NOT Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, custId);
            ps.setInt(2, apptId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentId = rs.getInt("Appointment_ID");
                int customerId = rs.getInt("Customer_ID");
                if (start.isEqual(apptStart) || end.isEqual(apptEnd)) {
                    return true;
                } else if ((apptStart.isAfter(start) && apptStart.isBefore(end)) ||
                        ((apptEnd.isAfter(start)) && (apptEnd.isBefore(end) || apptEnd.isEqual(end))) ||
                        (apptStart.isBefore(start) && apptEnd.isAfter(end))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method used to check if an appointment is scheduled within fifteen minutes of login, based on current time
     * @throws SQLException For throwing SQLException
     */
    public static void appointmentCheck() throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime nextAppt = LocalDateTime.now().plusMinutes(15);
        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? and ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.valueOf(localDateTime));
        ps.setTimestamp(2, Timestamp.valueOf(nextAppt));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            LocalDateTime nextApptDateTime = rs.getTimestamp("Start").toLocalDateTime();
            System.out.println(appointmentId);
            System.out.println(nextApptDateTime);
            long differenceBetweenTimes = ChronoUnit.MINUTES.between(localDateTime, nextApptDateTime);
            if (differenceBetweenTimes <= 15 && differenceBetweenTimes >= 0) {
                System.out.println(differenceBetweenTimes);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Within Fifteen Minutes");
                alert.setHeaderText("There Is An Appointment Within Fifteen Minutes.");
                alert.setContentText("Appointment for Appointment ID: " + appointmentId
                        + " at " + nextApptDateTime + " coming up within fifteen minutes.");
                alert.showAndWait();
            }
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("No Appointments Within Fifteen Minutes");
            alert2.setHeaderText("There Are No Appointments Within Fifteen Minutes.");
            alert2.setContentText("No appointments within fifteen minutes.");
            alert2.showAndWait();
        }
    }

    /**
     * Method used to filter population of type combobox for second report in reports screen
     * @param comboMonth Parameter used to access month combobox selection for second report in reports screen
     * @param comboType Parameter used to establish filtered value in type combobox based on selection in month
     *                  combobox for second report in reports screen
     * @throws SQLException For throwing SQLException
     */

    // Learned about how use MONTHNAME function in MySQL from the following URLs
    //    https://www.w3resource.com/mysql/date-and-time-functions/mysql-monthname-function.php
//    https://stackoverflow.com/questions/6731854/mysql-select-all-from-table-where-month
    public static void reportsTypeFiltering(ComboBox<String> comboMonth, ComboBox<String> comboType) throws SQLException {
        String start = comboMonth.getValue();
        String sql = "SELECT DISTINCT Type FROM appointments WHERE MONTHNAME(Start) = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, start);
        ResultSet rs = ps.executeQuery();
        comboType.getItems().clear();
        while (rs.next()) {

            comboType.getItems().add(rs.getString("Type"));
        }

        ps.close();
        rs.close();
    }

    /**
     * Method used to measure total number of appointments for second report in reports screen
     * @param comboMonth Parameter used to access selected value in month combobox in reports screen
     * @param comboType Parameter used to access selected value in type combobox in reports screen
     * @param label Parameter used to display results of method
     * @throws SQLException For throwing SQLException
     */
    // Learned about how to used MONTHNAME function and DISTINCT COUNT function from the following URLs
    //    https://www.w3resource.com/mysql/date-and-time-functions/mysql-monthname-function.php
//    https://stackoverflow.com/questions/4483798/can-i-use-count-and-distinct-together
    public static void totalNumberOfAppointments(ComboBox<String> comboMonth, ComboBox<String> comboType, Label label) throws SQLException {
        String start = comboMonth.getValue();
        String type = comboType.getValue();

        String sql = "SELECT COUNT(Type) AS Type FROM appointments " +
                "WHERE MONTHNAME(Start) = ? AND Type = ?";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, start);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String typeResult = rs.getString("Type");
            System.out.println(typeResult);
            label.setText(typeResult);
        }
    }
}


