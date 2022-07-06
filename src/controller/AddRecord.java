package controller;

import database.CustomerDB;
import helper.InputValidation;
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
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class for the adding customer form
 */
public class AddRecord implements Initializable {
    /**
     * Text field for customer name
     */
    public TextField addNameTextField;
    /**
     * Text field for customer address
     */
    public TextField addAddressTextField;
    /**
     * Text field for customer postal code
     */
    public TextField addPostalCodeTextField;
    /**
     * Text field for customer phone number
     */
    public TextField addPhoneTextField;
    /**
     * Text field for customer ID
     */
    public TextField addIdTextField;
    /**
     * Button for canceling adding customer
     */
    public Button addCancelButton;
    /**
     * Button for adding customer
     */
    public Button addButton;
    /**
     * Combobox for countries
     */
    public ComboBox<Country> comboCountry;
    /**
     * Combobox for first level divisions
     */
    public ComboBox<Division> comboDivision;

    /**
     * Initializing method for adding customer form
     *
     * @param url            Variable for url
     * @param resourceBundle Variable for ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addIdTextField.setDisable(true);
        addIdTextField.setPromptText("Auto Gen - Disabled");
        comboCountry.setItems(ListManager.allCountries);

        InputValidation.textNumberValidation(addPhoneTextField);
        InputValidation.textFieldStringValidation(addNameTextField);
        InputValidation.textFieldStringValidation(addAddressTextField);
        InputValidation.textNumberValidation(addPostalCodeTextField);
    }

    /**
     * Method for handling add button in add customer form
     *
     * @param actionEvent Variable for action event
     * @throws IOException  for throwing IOException
     * @throws SQLException for throwing SQLException
     */
    public void addButtonAction(ActionEvent actionEvent) throws IOException, SQLException {

        try {
            String name = addNameTextField.getText();
            String address = addAddressTextField.getText();
            String postalCode = addPostalCodeTextField.getText();
            String phone = addPhoneTextField.getText();
            int divisionId = comboDivision.getValue().getDivisionId();

            if (addNameTextField.getText().isBlank() || addAddressTextField.getText().isBlank()
                    || addPostalCodeTextField.getText().isBlank() || addPhoneTextField.getText().isBlank()
                    || comboCountry.getValue() == null || comboDivision.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please Fill In All Required Information");
                alert.setHeaderText("Please Fill In All Required Information");
                alert.setContentText("Please Fill In Required Information");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Please Confirm Adding Customer");
                alert.setHeaderText("Do You Want To Add A Customer?");
                alert.setContentText("Please confirm.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CustomerDB.addRecord(name, address, postalCode, phone, divisionId);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1200, 800);
                    stage.setScene(scene);
                    stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
                    stage.centerOnScreen();
                    stage.show();

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

    /**
     * Method for handling cancel button in add customer form
     *
     * @param actionEvent Variable for action event
     * @throws IOException For throwing IOException
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
     * Method for handling first level division combobox in add customer form
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



