package controller;


import database.AppointmentDB;
import database.CustomerDB;
import helper.Dialogs;
import helper.Search;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.BusinessCustomer;
import model.Customer;
import model.MarketingCustomer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Class for main screen
 */
public class MainScreen implements Initializable {

    /**
     * Table view for appointments
     */
    public TableView<Appointment> appointmentsTable;
    /**
     * Table view for customers
     */
    public TableView<Customer> recordsTable;
    /**
     * Label showing customers table
     */
    public Label recordsTopLabel;
    /**
     * User ID column in customer table
     */
    public TableColumn<Customer, Integer> recordsUserIdCol;
    /**
     * Name column in customer table
     */
    public TableColumn<Customer, String> recordsCustNameCol;
    /**
     * Address column in customer table
     */
    public TableColumn<Customer, String> recordsAddressCol;
    /**
     * Phone number column in customer table
     */
    public TableColumn<Customer, String> recordsPhoneCol;
    /**
     * Postal code column in customer table
     */
    public TableColumn<Customer, String> recordsPostalCol;
    /**
     * Country column in customer table
     */
    public TableColumn<Customer, String> recordsCountryCol;
    /**
     * Division column in customer table
     */
    public TableColumn<Customer, String> recordsDivisionCol;
    /**
     * Button for exiting program
     */
    public Button apptExitButton;
    /**
     * Button for deleting customer from customer table
     */
    public Button customerDeleteButton;
    /**
     * Button for updating a customer
     */
    public Button customerUpdateButton;
    /**
     * Button for adding a customer
     */
    public Button customerAddButton;
    /**
     * Appointment ID column in appointment table
     */
    public TableColumn<Appointment, Integer> apptApptIdCol;
    /**
     * Title column in appointment table
     */
    public TableColumn<Appointment, String> apptTitleCol;
    /**
     * Description column in appointment table
     */
    public TableColumn<Appointment, String> apptDescriptionCol;
    /**
     * Location column in appointment table
     */
    public TableColumn<Appointment, String> apptLocationCol;
    /**
     * Contact column in appointment table
     */
    public TableColumn<Appointment, String> apptContactCol;
    /**
     * Type column in appointment table
     */
    public TableColumn<Appointment, String> apptTypeCol;
    /**
     * Starting appointment date and time column in appointments table
     */
    public TableColumn<Appointment, String> apptStartDateTimeCol;
    /**
     * Ending appointment date and time column in appointments table
     */
    public TableColumn<Appointment, String> apptEndDateTimeCol;
    /**
     * Customer ID column in appointments table
     */
    public TableColumn<Appointment, Integer> apptCustomerIdCol;
    /**
     * User ID column in appointments table
     */
    public TableColumn<Appointment, Integer> apptUserIdCol;
    /**
     * Button for deleting appointments
     */
    public Button apptDeleteButton;
    /**
     * Button for updating appointments
     */
    public Button apptUpdateButton;
    /**
     * Button for adding appointments
     */
    public Button apptAddButton;
    /**
     * Radio button for showing all appointments
     */
    public RadioButton apptAllRadioButton;
    /**
     * Radio button for showing appointments by month
     */
    public RadioButton apptMonthRadioButton;
    /**
     * Radio button for showing appointments by week
     */
    public RadioButton apptWeekRadioButton;
    /**
     * Toggle group for radio buttons
     */
    public ToggleGroup toggleGroup;
    /**
     * Button for going to reports screen
     */
    public Button apptReportsButton;
    /**
     * Text Field for search for customers in records table
     */
    public TextField mainCustomerSearchField;
    public TextField mainApptSearchField;

    public Label mainTypeLabel;

    /**
     * Method for initializing main screen
     *
     * @param url            Variable for url
     * @param resourceBundle Variable for resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            recordsTable.setItems(CustomerDB.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }

        recordsUserIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        recordsCustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        recordsAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        recordsPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        recordsPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        recordsCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        recordsDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

        try {
            appointmentsTable.setItems(AppointmentDB.getAllAppointments());
        } catch (Exception e) {
            e.printStackTrace();
        }
        apptApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    public void receiveFromLogin(int i) {

        Customer customer = new Customer();
        Customer businessCustomer = new BusinessCustomer();
        Customer marketingCustomer = new MarketingCustomer();

        if (i == 0) {
            mainTypeLabel.setText(marketingCustomer.message());
        }
        else if (i == 1) {
            mainTypeLabel.setText(businessCustomer.message());
        } else {
            mainTypeLabel.setText(customer.message());
        }

    }

    /**
     * Method for handling using exit button
     */
    public void apptExitButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Program");
        alert.setHeaderText("Are You Sure You Want To Exit?");
        alert.setContentText("Exit Program?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) apptExitButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Method for handling using customer delete button
     *
     * @throws Exception For throwing exception
     */
    public void customerDeleteButtonAction() throws Exception {
        Customer customer = recordsTable.getSelectionModel().getSelectedItem();

        if (customer == null) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Please Make A Selection");
            alert2.setHeaderText("Please select a customer record.");
            alert2.setContentText("Please make a selection.");
            alert2.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Please confirm deletion.");
            alert.setContentText("If you proceed, all appointments associated with the selected customer will also " +
                    "be deleted. Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentDB.deleteAssociatedAppointment(customer, appointmentsTable);
                CustomerDB.deleteRecord(customer, recordsTable);
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Customer Removed");
                alert3.setHeaderText("Customer has been removed.");
                alert3.setContentText("You have removed this customer.");
                alert3.showAndWait();

                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setTitle("Associated Appointments Removed");
                alert4.setHeaderText("Appointments Removed");
                alert4.setContentText("Associated appointments for Customer ID: " + customer.getCustomerId() +
                        " removed.");
                alert4.showAndWait();
            }
        }
    }

    /**
     * Method for handling using customer update button
     *
     * @param actionEvent Variable for action event
     * @throws IOException For throwing IOException
     */
    public void customerUpdateButtonAction(ActionEvent actionEvent) throws IOException {
        Customer customer = recordsTable.getSelectionModel().getSelectedItem();
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Was Selected");
            alert.setContentText("Please select a customer.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("/view/UpdateRecord.fxml")));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            UpdateRecord controller = loader.getController();
            controller.receiveDataToUpdateRecord(recordsTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
            stage.centerOnScreen();
            stage.show();
        }

    }

    /**
     * Method for handling using add customer button
     *
     * @param actionEvent Variable for action event
     * @throws IOException For throwing IOException
     */
    public void customerAddButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddRecord.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
        stage.setTitle("Add Customer Information");
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Method for handling using delete appointment button
     *
     * @throws SQLException For throwing SQLException
     */
    public void apptDeleteButtonAction() throws SQLException {
        Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();

        if (appointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please Make A Selection");
            alert.setHeaderText("Please select a customer appointment.");
            alert.setContentText("Please make a selection.");
            alert.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirm Deletion");
            alert2.setHeaderText("Please confirm deletion.");
            alert2.setContentText("Are you sure?");

            Optional<ButtonType> result = alert2.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentDB.deleteAppointment(appointment, appointmentsTable);
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Customer Removed");
                alert3.setHeaderText("Appointment ID: " + apptApptIdCol.getCellData(appointment)
                        + " of Type: " + apptTypeCol.getCellData(appointment) + " has been removed.");
                alert3.setContentText("You have removed this appointment");
                alert3.showAndWait();
            }
        }
    }

    /**
     * Method for handling using update appointment button
     *
     * @param actionEvent Variable for action event
     * @throws Exception For throwing Exception
     */
    public void apptUpdateButtonAction(ActionEvent actionEvent) throws Exception {
        Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Was Selected");
            alert.setContentText("Please select an appointment.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("/view/UpdateAppointments.fxml")));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            UpdateAppointments controller = loader.getController();
            controller.receiveDataToUpdateAppointment(appointmentsTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
            stage.centerOnScreen();
            stage.show();
        }
    }

    /**
     * Method for handling add appointment button
     *
     * @param actionEvent Variable for action event
     * @throws IOException For throwing IOException
     */
    public void apptAddButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointments.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Add Appointment Information");
        stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Method for handling using month radio button
     *
     * @throws Exception For throwing Exception
     */
    public void apptMonthRadioAction() throws Exception {
        System.out.println("Month sort button selected");
        filterByMonth();
    }

    /**
     * Method for handling using week radio button
     *
     * @throws Exception For throwing Exception
     */
    public void apptWeekRadioAction() throws Exception {
        System.out.println("Week sort button selected");
        filterByWeek();
    }

    /**
     * Method for handling using all radio button
     *
     * @throws Exception For throwing Exception
     */
    public void apptAllRadioAction() throws Exception {
        System.out.println("All button selected");
        filterAll();

    }

    /**
     * Method used for filtering appointments table by month
     * Lambda expression #1: Used to set first condition for filtering appointments table by month,
     * by ensuring beginning time of filtering range is after the current system time
     * Lambda expression #2: Used to set second condition for filtering appointments table by month,
     * by ensuring ending time of filtering range is one month after the current system time
     *
     * @throws Exception For throwing Exception
     */
    public void filterByMonth() throws Exception {
        FilteredList<Appointment> filteredList = new FilteredList<>(AppointmentDB.getAllAppointments());
        appointmentsTable.setItems(filteredList);

        Predicate<Appointment> startOfFilterRange = i -> i.getStart().isAfter(LocalDateTime.now());
        Predicate<Appointment> endOfFilterRange = i -> i.getStart().isBefore(LocalDateTime.now().plusMonths(1));
        Predicate<Appointment> filter = startOfFilterRange.and(endOfFilterRange);

        filteredList.setPredicate(filter);
    }

    /**
     * Method used for filtering appointments by week
     * Lambda expression #3: Used to set first condition for filtering appointments table by week,
     * by ensuring beginning time of filtering range is after the current system time
     * Lambda expression #4: Used to set second condition for filtering appointments table by week,
     * by ensuring ending time of filtering range is one week after the current system time
     *
     * @throws Exception For throwing Exception
     */
    public void filterByWeek() throws Exception {
        FilteredList<Appointment> filteredList = new FilteredList<>(AppointmentDB.getAllAppointments());
        appointmentsTable.setItems(filteredList);

        Predicate<Appointment> startOfFilterRange = i -> i.getStart().isAfter(LocalDateTime.now());
        Predicate<Appointment> endOfFilterRange = i -> i.getStart().isBefore(LocalDateTime.now().plusWeeks(1));
        Predicate<Appointment> filter = startOfFilterRange.and(endOfFilterRange);

        filteredList.setPredicate(filter);
    }

    /**
     * Method used to show all appointments in appointments table
     *
     * @throws Exception For throwing Exception
     */
    public void filterAll() throws Exception {
        FilteredList<Appointment> filteredList = new FilteredList<>(AppointmentDB.getAllAppointments());
        appointmentsTable.setItems(filteredList);

        filteredList.setPredicate(null);
    }

    /**
     * Method used for handling using reports button
     *
     * @param actionEvent Variable for action event
     * @throws IOException For throwing IOException
     */
    public void apptReportsButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Reports.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.setTitle("Reports");
        stage.centerOnScreen();
        stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
        stage.show();

    }

    /**
     * Method used to handling searching for customers in corresponding text field
     *
     * @param actionEvent Variable for action event
     */
    public void mainCustomerSearchAction(ActionEvent actionEvent) throws Exception {
        String q = mainCustomerSearchField.getText();

        ObservableList<Customer> customerName = Search.lookupCustomer(q);
        if (customerName.size() == 0) {
            try {
                int id = Integer.parseInt(q);
                Customer c = Search.lookupCustomer(id);
                customerName.add(c);
            } catch (ClassCastException e) {
                Dialogs.failedSearchPrompt();
            }
        }
        recordsTable.setItems(customerName);
        mainCustomerSearchField.setText(Integer.toString(customerName.size()));
        mainCustomerSearchField.setText((""));
    }

    public void mainApptSearchAction(ActionEvent actionEvent) throws Exception {
        String q = mainApptSearchField.getText();

        ObservableList<Appointment> appointmentId = Search.lookupAppointment(q);
        if (appointmentId.size() == 0) {
            try {
                int id = Integer.parseInt(q);
                Appointment a = Search.lookupAppointment(id);
                appointmentId.add(a);
            } catch (ClassCastException | NumberFormatException | InvocationTargetException e) {
                Dialogs.failedSearchPrompt();
            }
        }
        appointmentsTable.setItems(appointmentId);
        mainCustomerSearchField.setText(Integer.toString(appointmentId.size()));
        mainCustomerSearchField.setText((""));
    }

}
