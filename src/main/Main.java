package main;

import database.ContactsDB;
import database.CountryDB;
import database.DivisionDB;
import database.UserDB;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;

/**
 * Class used for initializing application
 */
public class Main extends Application {

    /**
     * Method used to set stage for application
     * @param stage Parameter used for stage setting
     * @throws Exception For throwing Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScreen.fxml")));
        stage.setTitle("Scheduling System");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**
     * Method used to begin application
     * @param args Parameter used as a standard in main method
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        ContactsDB.select();
        CountryDB.select();
        DivisionDB.select();
        UserDB.select();
//        Locale.setDefault(new Locale("en"));

        launch(args);

        JDBC.closeConnection();
    }

}
