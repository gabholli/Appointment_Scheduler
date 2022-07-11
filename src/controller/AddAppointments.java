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
 * Class for the add appointments screen
 */
public class AddAppointments implements Initializable {
    /**
     * Text field for appointment title
     */
    public TextField addApptTitleTextField;
    /**
     * Text field for appointment description
     */
    public TextField addAppDescTextField;
    /**
     * Text field for appointment location
     */
    public TextField addApptLocationTextField;
    /**
     * Button for adding appointment
     */
    public Button addApptButton;
    /**
     * Button for canceling adding appointment
     */
    public Button addApptCancelButton;
    /**
     * Text field for appointment ID
     */
    public TextField addApptIdTextField;
    /**
     * Text field for appointment type
     */
    public TextField addApptTypeTextField;
    /**
     * Combobox for contacts
     */
    public ComboBox<Contacts> comboContact;
    /**
     * Combobox for appointment ending times
     */
    public ComboBox<LocalTime> comboEnd;
    /**
     * Combobox for appointments starting times
     */
    public ComboBox<LocalTime> comboStart;
    /**
     * Combobox for user ID
     */
    public ComboBox<Customer> comboCustomerId;
    /**
     * Combobox for customer ID
     */
    public ComboBox<User> comboUserId;
    /**
     * Datepicker for selecting appointment date
     */
    public DatePicker addDatePicker;

    /**
     * Method for initializing add appointment form
     *
     * @param url            Variable for url
     * @param resourceBundle Variable for ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addApptIdTextField.setPromptText("Auto Gen - Disabled");
        addApptIdTextField.setDisable(true);
        Time.loadTime();
        comboContact.setItems(ListManager.allContacts);
        try {
            comboCustomerId.setItems(CustomerDB.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }
        comboUserId.setItems(ListManager.allUsers);
        comboStart.setItems(ListManager.allStartTimes);
        comboEnd.setItems(ListManager.allEndTimes);

        InputValidation.textFieldStringValidation(addApptTitleTextField);
        InputValidation.textFieldStringValidation(addAppDescTextField);
        InputValidation.textFieldStringValidation(addApptLocationTextField);
        InputValidation.textFieldStringValidation(addApptTypeTextField);

    }

    /**
     * Method for handling add button in add appointment form
     *
     * @param actionEvent Variable for action event
     * @throws IOException  for throwing IOException
     * @throws SQLException for throwing SQLException
     */
    public void addButtonAction(ActionEvent actionEvent) throws IOException, SQLException {
        try {
            String title = addApptTitleTextField.getText();
            String desc = addAppDescTextField.getText();
            String location = addApptLocationTextField.getText();
            String type = addApptTypeTextField.getText();
            LocalDate localDate = addDatePicker.getValue();
            LocalTime start = comboStart.getValue();
            LocalTime end = comboEnd.getValue();
            LocalDateTime localStartDateTime = LocalDateTime.of(localDate, start);
            LocalDateTime localEndDateTime = LocalDateTime.of(localDate, end);
            int customerId = comboCustomerId.getValue().getCustomerId();
            int userId = comboUserId.getValue().getUserId();
            int contactId = comboContact.getValue().getContactId();

            if (addApptTitleTextField.getText().isBlank() || addAppDescTextField.getText().isBlank() ||
                    addApptLocationTextField.getText().isBlank() || addApptTypeTextField.getText().isBlank()) {
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
                alert3.setTitle("Please Confirm Adding Appointment");
                alert3.setHeaderText("Do You Want To Add An Appointment?");
                alert3.setContentText("Please confirm.");
                Optional<ButtonType> result = alert3.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (AppointmentDB.overlapAddCheck(localStartDateTime, localEndDateTime, customerId)) {
                        System.out.println("Overlapping appointment.");
                        Alert alert4 = new Alert(Alert.AlertType.ERROR);
                        alert4.setTitle("Cannot Add Appointment");
                        alert4.setHeaderText("Appointment Overlap Encountered.");
                        alert4.setContentText("Please Correct.");
                        alert4.showAndWait();
                    } else {
                        System.out.println("No overlapping appointments.");
                        AppointmentDB.addAppointment(title, desc, location, type, localStartDateTime, localEndDateTime, customerId, userId, contactId);
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
            alert.setHeaderText("Please Fill In All Required Information.");
            alert.setContentText("Please Fill In Required Information.");
            alert.showAndWait();
        }
    }

    /**
     * Method for handling cancel button in add appointment form
     *
     * @param actionEvent Variable for action event
     * @throws IOException for throwing IOException
     */
    public void addCancelButtonAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Addition");
        alert.setHeaderText("Are You Sure?");
        alert.setContentText("Exit Form?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
            stage.centerOnScreen();
            stage.show();
        }
    }

    public void initializeContact() {
    }

    public void initializeStart() {
    }

    public void initializeEnd() {
    }
}
