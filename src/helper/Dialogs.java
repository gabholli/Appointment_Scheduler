package helper;

import javafx.scene.control.Alert;

/**
 * Method used to allow for easier creation of alert dialogs
 */
public class Dialogs {

    /**
     * Method used to generate alerts
     * @param alertType Parameter for alert type needed
     * @param title Parameter for setting alert window title
     * @param message Parameter for setting alert message in body of window
     */
    public static void displayAlert(javafx.scene.control.Alert.AlertType alertType, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(null);
        alert.showAndWait();
    }

    /**
     * Method for generating a prompt when searching for customers
     * in main screen fails
     */
    public static void failedSearchPrompt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nothing was found.");
        alert.setHeaderText(null);
        alert.setContentText("Search produced no results.");

        alert.showAndWait();
    }
}
