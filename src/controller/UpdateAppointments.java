package controller;

import database.AppointmentDB;
import database.CustomerDB;
import helper.InputValidation;
import helper.ListManager;
import helper.Time;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contacts;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class for update appointment form
 */
public class UpdateAppointments implements Initializable {
    /**
     * Text field for appointment ID
     */
    public TextField updateApptIdTextField;
    /**
     * Text field for title
     */
    public TextField updateApptTitleTextField;
    /**
     * Text field for description
     */
    public TextField updateAppDescTextField;
    /**
     * Text field for location
     */
    public TextField updateApptLocationTextField;
    /**
     * Text field for type
     */
    public TextField updateApptTypeTextField;
    /**
     * Button used to confirm updating appointment
     */
    public Button updateApptButton;
    /**
     * Button used to cancel updating appointment and return to main screen
     */
    public Button updateApptCancelButton;
    /**
     * Combobox used to select appointment starting time
     */
    public ComboBox<LocalTime> comboStart;
    /**
     * Combobox used to select appointment ending time
     */
    public ComboBox<LocalTime> comboEnd;
    /**
     * Combobox used to select customer ID
     */
    public ComboBox<Customer> comboCustomerId;
    /**
     * Combobox used to select user ID
     */
    public ComboBox<User> comboUserId;
    /**
     * Combobox used to select contact
     */
    public ComboBox<Contacts> comboContact;
    /**
     * Date picker used to select appointment date
     */
    public DatePicker updateDatePicker;
    /**
     * Variable used to allow retrieving data in data receiving method
     */
    Appointment selectedAppointment;

    /**
     * Method used to initialize update appointment form
     *
     * @param url            Variable for url
     * @param resourceBundle Variable for resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateApptIdTextField.setDisable(true);
        Time.loadTime();

        InputValidation.textFieldStringValidation(updateApptTitleTextField);
        InputValidation.textFieldStringValidation(updateAppDescTextField);
        InputValidation.textFieldStringValidation(updateApptLocationTextField);
        InputValidation.textFieldStringValidation(updateApptTypeTextField);
    }

    /**
     * Method used to receive data from main screen
     *
     * @param appointment Parameter used to retrieve appointment data from main screen
     * @throws Exception For throwing Exception
     */
    public void receiveDataToUpdateAppointment(Appointment appointment) throws Exception {

        selectedAppointment = appointment;
        updateApptIdTextField.setText(String.valueOf(selectedAppointment.getApptId()));
        updateApptTitleTextField.setText(selectedAppointment.getTitle());
        updateAppDescTextField.setText(selectedAppointment.getDescription());
        updateApptLocationTextField.setText(selectedAppointment.getLocation());
        updateApptTypeTextField.setText(selectedAppointment.getType());
        comboContact.setItems(ListManager.allContacts);
        comboCustomerId.setItems(CustomerDB.getAllCustomers());
        comboUserId.setItems(ListManager.allUsers);
        updateDatePicker.setValue(LocalDate.now());
        comboStart.setItems(ListManager.allStartTimes);
        comboEnd.setItems(ListManager.allEndTimes);
        ListManager.apptContactsComboFiltering(selectedAppointment, comboContact);
        ListManager.apptStartComboFiltering(selectedAppointment, comboStart);
        ListManager.apptCustomerComboFiltering(selectedAppointment, comboCustomerId);
        ListManager.apptEndComboFiltering(selectedAppointment, comboEnd);
        ListManager.apptUserComboFiltering(selectedAppointment, comboUserId);
        ListManager.apptDatePickerFiltering(selectedAppointment, updateDatePicker);
    }

    public void initializeContact() {
    }

    public void initializeStart() {
    }

    public void initializeEnd() {
    }

    /**
     * Method used to handle using cancel button in update appointment form
     *
     * @param actionEvent
     * @throws IOException
     */
    public void addCancelButtonAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Update");
        alert.setHeaderText("Are You Sure?");
        alert.setContentText("Exit Form?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
            stage.setTitle("Scheduling System");
            stage.centerOnScreen();
            stage.show();
        }
    }

    /**
     * Method for handling using update button in update appointment form
     *
     * @param actionEvent Parameter for action event
     * @throws SQLException For throwing SQLException
     * @throws IOException  For throwing IOException
     */
    public void updateButtonAction(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            String title = updateApptTitleTextField.getText();
            String desc = updateAppDescTextField.getText();
            String location = updateApptLocationTextField.getText();
            String type = updateApptTypeTextField.getText();
            LocalDate localDate = updateDatePicker.getValue();
            LocalTime start = comboStart.getValue();
            LocalTime end = comboEnd.getValue();
            LocalDateTime localStartDateTime = LocalDateTime.of(localDate, start);
            LocalDateTime localEndDateTime = LocalDateTime.of(localDate, end);
            int custId = comboCustomerId.getValue().getCustomerId();
            int userId = comboUserId.getValue().getUserId();
            int contactId = comboContact.getValue().getContactId();
            int apptId = Integer.parseInt(updateApptIdTextField.getText());


            if (updateApptIdTextField.getText().isBlank() || updateApptTitleTextField.getText().isBlank() ||
                    updateAppDescTextField.getText().isBlank() || updateApptLocationTextField.getText().isBlank() ||
                    comboContact.getValue() == null || updateApptTypeTextField.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please Fill In All Required Information");
                alert.setHeaderText("Please Fill In All Required Information");
                alert.setContentText("Please Fill In Required Information");
                alert.showAndWait();
            } else if (localEndDateTime.isBefore(localStartDateTime)) {
                System.out.println("End Time Before Start Time");
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Please Re-Adjust Appointment Start And End Times");
                alert2.setHeaderText("Appointment End Time Cannot Be Before Start Time.");
                alert2.setContentText("Please Correct.");
                alert2.showAndWait();
            } else {
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                alert3.setTitle("Please Confirm Updating Appointment");
                alert3.setHeaderText("Do You Want To Update?");
                alert3.setContentText("Please confirm.");
                Optional<ButtonType> result = alert3.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (AppointmentDB.overlapUpdateCheck(localStartDateTime, localEndDateTime, custId, apptId)) {
                        System.out.println("Overlap");
                        Alert alert4 = new Alert(Alert.AlertType.ERROR);
                        alert4.setTitle("Cannot Add Appointment");
                        alert4.setHeaderText("Appointment Overlap Encountered.");
                        alert4.setContentText("Please Correct.");
                        alert4.showAndWait();
                    } else {
                        System.out.println("No overlap.");
                        AppointmentDB.updateAppointment(title, desc, location, type, localStartDateTime,
                                localEndDateTime, custId, userId, contactId, apptId);
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1200, 800);
                        stage.setScene(scene);
                        stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
                        stage.centerOnScreen();
                        stage.show();
                    }
                }
            }
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please Fill In All Required Information");
            alert.setHeaderText("Please Fill In All Required Information");
            alert.setContentText("Please Fill In Required Information");
            alert.showAndWait();
        }
    }
}
