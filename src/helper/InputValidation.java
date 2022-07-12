package helper;


import javafx.scene.control.TextField;

public class InputValidation {

    /**
     * Method for validating text fields, allowing only numbers or dashes
     * @param tf Parameter for using a given text field
     */
    public static void textNumberValidation(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d *")) return;
            tf.setText(newValue.replaceAll("[^-\\d ]", ""));
        });
    }

    /**
     * Method for validating text fields, allwoing only letters, numbers, or dashes
     * @param tf Parameter for using a given text field
     */
    public static void textFieldStringValidation(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("[a-zA-Z0-9 ]")) return;
            tf.setText(newValue.replaceAll("[^-a-zA-Z0-9 ]", ""));
        });
    }

}





