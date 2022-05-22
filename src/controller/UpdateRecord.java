package controller;

import database.CustomerDB;
import helper.ListManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class for updating customer record form
 */
public class UpdateRecord implements Initializable {

    /**
     * Combobox for selecting customer country
     */
    public ComboBox<Country> comboCountry;
    /**
     * Combobox for selecting customer first level division
     */
    public ComboBox<Division> comboDivision;
    /**
     * Variable for helping in retrieval of customer data from main screen
     */
    Customer selectedCustomer;
    /**
     * Text field for customer name
     */
    public TextField nameTextField;
    /**
     * Text field for customer address
     */
    public TextField addressTextField;
    /**
     * Text field for customer postal code
     */
    public TextField postalCodeTextField;
    /**
     * Text field for customer ID
     */
    public TextField IdTextField;
    /**
     * Text field for customer phone number
     */
    public TextField phoneTextField;
    /**
     * Button used to cancel updating customer record
     */
    public Button cancelButton;
    /**
     * Button used to comfirm updating customer record
     */
    public Button updateButton;

    /**
     * Method used to initialize update customer record form
     * @param url Variable for url
     * @param resourceBundle Variable for resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IdTextField.setDisable(true);
    }

    /**
     * Method used to receive data from main screen to update customer record form
     * @param customer Parameter used for receiving customer data
     */
    public void receiveDataToUpdateRecord(Customer customer) {

        selectedCustomer = customer;
        IdTextField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameTextField.setText(selectedCustomer.getCustomerName());
        addressTextField.setText(selectedCustomer.getAddress());
        postalCodeTextField.setText(selectedCustomer.getPostalCode());
        phoneTextField.setText(selectedCustomer.getPhoneNumber());
        comboCountry.setItems(ListManager.allCountries);
        ListManager.recordComboBoxFiltering(selectedCustomer, comboCountry, comboDivision);
    }

    /**
     * Method used to handle using cancel button in update customer record form
     * @param actionEvent Parameter for action event
     * @throws IOException For throwing IOException
     */
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {
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
            stage.setTitle("Scheduling System");
            stage.centerOnScreen();
            stage.show();
        }
    }

    /**
     * Method used to handling using update button in update customer record form
     * @param actionEvent Parameter for action event
     * @throws IOException For throwing IOException
     * @throws SQLException For throwing SQLException
     */
    public void updateButtonAction(ActionEvent actionEvent) throws IOException, SQLException {

        int id = Integer.parseInt(IdTextField.getText());
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneTextField.getText();
        int divisionId = comboDivision.getValue().getDivisionId();

        if (IdTextField.getText().isBlank() || nameTextField.getText().isBlank() || addressTextField.getText().isBlank()
                || postalCodeTextField.getText().isBlank() || phoneTextField.getText().isBlank()
                || comboCountry.getValue() == null || comboDivision.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please Fill In All Required Information");
            alert.setHeaderText("Please Fill In All Required Information");
            alert.setContentText("Please Fill In Required Information");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please Confirm Updating Customer");
            alert.setHeaderText("Do You Want To Update?");
            alert.setContentText("Please confirm.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CustomerDB.updateRecord(name, address, postalCode, phone, divisionId, id);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1200, 800);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } else {
                System.out.println("Update failed");
            }

        }
    }

    /**
     * Method used to handling using first level division combobox
     */
    public void initializeDivision() {
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        for (Division d : ListManager.allDivisions) {
            if (d.getCountryId() == comboCountry.getValue().getCountryId()) {
                divisionList.add(d);
            }
        }
        comboDivision.setItems(divisionList);
        comboDivision.getSelectionModel().selectFirst();
    }

}
