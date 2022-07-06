package controller;

import database.AppointmentDB;
import database.CustomerDB;
import helper.ListManager;
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
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Class for reports screen
 */
public class Reports implements Initializable {

    /**
     * Combobox used to select contacts
     */
    public ComboBox<Contacts> selectContactComboBox;
    /**
     * Table view containing appointment information filtered by
     * selected contact
     */
    public TableView<model.Appointment> contactsTable;
    /**
     * Appointment ID column in contacts table
     */
    public TableColumn<Object, Object> contactApptIdCol;
    /**
     * Title column in contacts table
     */
    public TableColumn<Object, Object> contactTitleCol;
    /**
     * Type column in contacts table
     */
    public TableColumn<Object, Object> contactTypeCol;
    /**
     * Description column in contact table
     */
    public TableColumn<Object, Object> contactDescCol;
    /**
     * Appointment starting time column in contacts table
     */
    public TableColumn<Object, Object> contactStartCol;
    /**
     * Appointment ending time column in contacts table
     */
    public TableColumn<Object, Object> contactEndCol;
    /**
     * Customer ID column in contacts table
     */
    public TableColumn<Object, Object> contactCustomerIdCol;
    /**
     * Button used to exit reports screen
     */
    public Button reportExitButton;
    /**
     * Combobox for selecting month in second report
     */
    public ComboBox<String> selectMonthComboBox;
    /**
     * Combobox for selecting type in second report
     */
    public ComboBox<String> selectTypeComboBox;
    /**
     * Button used to display number of unique customers in third report
     */
    public Button reportCustomerNumberButton;
    /**
     * Label used to display number of unique customers in third report
     */
    public Label reportShowCustomersLabel;
    /**
     * Label used to display number of appointments in second report
     */
    public Label reportApptResultsLabel;

    /**
     * Method used to initialize reports screen
     * @param url Variable for url
     * @param resourceBundle Variable for resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            contactsTable.setItems(AppointmentDB.getAllAppointments());
        } catch (Exception e) {
            e.printStackTrace();
        }

        contactApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        contactTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        contactEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        contactCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        selectContactComboBox.setItems(ListManager.allContacts);
        selectContactComboBox.setPromptText("Select Contact Here");
        selectMonthComboBox.setPromptText("Select Month");
        selectTypeComboBox.setPromptText("Select Type");
        ListManager.reportMonthComboFiltering();
        selectMonthComboBox.setItems(ListManager.allMonths);

    }

    /**
     * Method used to handle contact combobox in first report
     * Lambda expression #5: Used to filter contact table results if Anika Costa is selected
     * Lambda expression #6: Used to filter contact table results if Daniel Garcia is selected
     * Lambda expression #7: Used to filter contact table results if Li Lee is selected
     * @throws Exception For throwing Exception
     */
    public void selectContactAction() throws Exception {
        FilteredList<Appointment> filteredList = new FilteredList<>(AppointmentDB.getAllAppointments());
        contactsTable.setItems(filteredList);
        if (Objects.equals(selectContactComboBox.getValue().getContactName(), "Anika Costa")) {
            Predicate<Appointment> nameFilter = i -> Objects.equals(i.getContactName(), "Anika Costa");
            filteredList.setPredicate(nameFilter);
        } else if (Objects.equals(selectContactComboBox.getValue().getContactName(), "Daniel Garcia")) {
            Predicate<Appointment> nameFilter = i -> Objects.equals(i.getContactName(), "Daniel Garcia");
            filteredList.setPredicate(nameFilter);
        } else if (Objects.equals(selectContactComboBox.getValue().getContactName(), "Li Lee")) {
            Predicate<Appointment> nameFilter = i -> Objects.equals(i.getContactName(), "Li Lee");
            filteredList.setPredicate(nameFilter);
        }
    }

    /**
     * Method used to handle exit button in reports screen
     * @param actionEvent Variable for action event
     * @throws IOException Variable for IOException
     */
    public void reportExitButtonAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Go Back To Main Screen");
        alert.setHeaderText("Are You Sure You Want To Go Back?");
        alert.setContentText("Return To Main Screen?");

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
     * Method used to handle using month combobox in second report
     */
    public void selectMonthComboAction() {

        try {
            AppointmentDB.reportsTypeFiltering(selectMonthComboBox, selectTypeComboBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to handle using type combobox in second report
     * @throws SQLException For throwing SQLException
     */
    public void selectTypeComboAction() throws SQLException {
        AppointmentDB.totalNumberOfAppointments(selectMonthComboBox, selectTypeComboBox, reportApptResultsLabel);

    }

    /**
     * Method used to handle using button in third report to display number of unique customers
     */
    public void reportCustomerButtonAction() {
        try {
            CustomerDB.totalNumberOfCustomers(reportShowCustomersLabel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
