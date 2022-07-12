package controller;


import database.AppointmentDB;
import helper.Dialogs;
import helper.FileOutput;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class for the login screen
 */
public class LoginScreen implements Initializable {

    /**
     * Button for signing in
     */
    public Button loginSignInButton;
    /**
     * Label for user ID
     */
    public Label userIdLabel;
    /**
     * Button for canceling and exiting program
     */
    public Button loginCancelButton;
    /**
     * Label for password
     */
    public Label passwordLabel;
    /**
     * Label for zone ID
     */
    public Label zoneIDLabel;
    /**
     * Label for displaying users zone ID
     */
    public Label zoneIdDisplayLabel;
    /**
     * Label for showing what the program is for
     */
    public Label loginTopLabel;
    /**
     * Text field for entering username
     */
    public TextField loginUserNameTxtField;
    /**
     * Text field for entering password
     */
    public TextField loginPasswordTxtField;
    /**
     * Radio button to select oneself as business customer
     */
    public RadioButton loginBusinessRadioButton;
    /**
     * Radio button to select oneself as marketing customer
     */
    public RadioButton loginMarketingRadioButton;
    /**
     * Toggle group for radio buttons in login screen
     */
    public ToggleGroup loginToggle;
    /**
     * Variable for using resource bundle
     */
    ResourceBundle bundle;
    /**
     * Variable for using locale
     */
    Locale locale;

    /**
     * Method for initializing login screen
     *
     * @param url            Variable for url
     * @param resourceBundle Variable for ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        translation();
    }

    /**
     * Method for selecting appropriate translation
     */
    public void translation() {
        if (Locale.getDefault().getLanguage().equals("en"))
            loadLanguage("en");
        else if (Locale.getDefault().getLanguage().equals("fr"))
            loadLanguage("fr");
    }

    /**
     * Method for loading proper resource bundle entries
     *
     * @param language Parameter for selecting language
     */
    private void loadLanguage(String language) {
        locale = new Locale(language);
        bundle = ResourceBundle.getBundle("helper/Lang", locale);
        userIdLabel.setText(bundle.getString("UserID"));
        passwordLabel.setText(bundle.getString("Password"));
        loginSignInButton.setText(bundle.getString("SignIn"));
        loginCancelButton.setText(bundle.getString("Cancel"));
        loginTopLabel.setText(bundle.getString("Scheduler"));
        zoneIDLabel.setText(bundle.getString("ZoneID"));
        zoneIdDisplayLabel.setText(String.valueOf(ZoneId.systemDefault()));

    }

    /**
     * Method for handling using sign in button
     *
     * @param actionEvent Variable for action event
     * @throws Exception For throwing exception
     */
    public void loginSignInAction(ActionEvent actionEvent) throws Exception {
        String userName = loginUserNameTxtField.getText();
        String password = loginPasswordTxtField.getText();

        JDBC jdbc = new JDBC();
        boolean check = jdbc.validation(userName, password);

        if (!check) {
            System.out.println("Not validated");
            FileOutput.writeToFileFailure(userName);
            Dialogs.displayAlert(Alert.AlertType.ERROR, bundle.getString("WrongLogin"), bundle.getString("EnterCorrect"));

        } else {
            System.out.println("Validated");
            FileOutput.writeToFileSuccess(userName);
            Dialogs.displayAlert(Alert.AlertType.INFORMATION, bundle.getString("LoginSuccess"), bundle.getString("LoginSuccess"));
            AppointmentDB.appointmentCheck();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("/view/MainScreen.fxml")));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            MainScreen controller = loader.getController();
            controller.receiveFromLogin(loginToggle.getToggles().indexOf(loginToggle.getSelectedToggle()));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
            stage.centerOnScreen();
            stage.show();
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
//            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root, 1200, 800);
//            stage.setScene(scene);
//            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style1.css")).toExternalForm());
//            stage.centerOnScreen();
//            stage.show();
        }
    }

    /**
     * Method for handling using cancel button
     */
    public void loginCancelAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(bundle.getString("CancelTitle"));
        alert.setHeaderText(bundle.getString("CancelHeader"));
        alert.setContentText(bundle.getString("CancelContent"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) loginCancelButton.getScene().getWindow();
            stage.close();
        }
    }


    public void loginMarketingRadioButtonAction(ActionEvent actionEvent) {
        System.out.println(loginToggle.getToggles().indexOf(loginToggle.getSelectedToggle()));
    }

    public void loginBusinessRadioButtonAction(ActionEvent actionEvent) {
        System.out.println(loginToggle.getToggles().indexOf(loginToggle.getSelectedToggle()));

    }

}
