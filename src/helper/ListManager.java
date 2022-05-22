package helper;

import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import model.*;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Class used for observable list management
 */
public class ListManager {
    /**
     * Observable list for countries
     */
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    /**
     * Observable list for first level divisions
     */
    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /**
     * Observable list for contacts
     */
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
    /**
     * Observable list for users
     */
    public static ObservableList<User> allUsers = FXCollections.observableArrayList();
    /**
     * Observable list for appointment start times
     */
    public static ObservableList<LocalTime> allStartTimes = FXCollections.observableArrayList();
    /**
     * Observable list for appointment end times
     */
    public static ObservableList<LocalTime> allEndTimes = FXCollections.observableArrayList();
    /**
     * Observable list for appointment months
     */
    public static ObservableList<String> allMonths = FXCollections.observableArrayList();

    /**
     * Method used to set appropriate values for country and first level division comboboxes
     * in update customer record form
     * @param customer Parameter used for accessing country ID
     * @param country Parameter used for setting country values in country combobox in update
     *                customer record form
     * @param division Parameter used for setting first level division values in division combobox
     *                 in update customer record form, based on selection in country combobox
     */
    public static void recordComboBoxFiltering(Customer customer, ComboBox<Country> country, ComboBox<Division> division) {
        for (Country c : ListManager.allCountries) {
            if (c.getCountryId() == customer.getCountryId()) {
                country.setValue(c);
                break;
            }
        }

        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        for (Division d : ListManager.allDivisions) {
            if (d.getCountryId() == country.getValue().getCountryId()) {
                divisionList.add(d);
            }
        }
        division.setItems(divisionList);
        for (Division d : divisionList) {
            if (d.getDivisionId() == customer.getDivisionId()) {
                division.setValue(d);
                break;
            }
        }
    }

    /**
     * Method used to set values for contacts combobox in update appointment form
     * @param appointment Parameter used for accessing contact name
     * @param comboContact Parameter used to allow contacts combobox to populate from observable list
     */
    public static void apptContactsComboFiltering(Appointment appointment, ComboBox<Contacts> comboContact) {
        for (Contacts c : ListManager.allContacts) {
            if (Objects.equals(c.getContactName(), appointment.getContactName()))
                comboContact.setValue(c);
        }
    }

    /**
     * Method used to populate starting time combobox in update appointment form
     * @param appointment Parameter allowing for accessing start times
     * @param comboStart Parameter used to populate start combobox in update appoinment
     *                   form
     */
    public static void apptStartComboFiltering(Appointment appointment, ComboBox<LocalTime> comboStart) {
        for (LocalTime t : ListManager.allStartTimes) {
            if (t.equals(appointment.getStart().toLocalTime())) {
                comboStart.setValue(t);
            }
        }
    }

    /**
     * Method used to populate ending time combobox in update appointment form
     * @param appointment Parameter used to access ending times
     * @param comboEnd Parameter used to populate end combobox in update appointment form
     */
    public static void apptEndComboFiltering(Appointment appointment, ComboBox<LocalTime> comboEnd) {
        for (LocalTime t : ListManager.allEndTimes) {
            if (t.equals(appointment.getEnd().toLocalTime())) {
                comboEnd.setValue(t);
            }
        }
    }

    /**
     * Method used to set initial value of date picker in update appointments screen
     * @param appointment Parameter used to access appointment start date
     * @param datePicker Parameter used to set initial value in date picker
     */
    public static void apptDatePickerFiltering(Appointment appointment, DatePicker datePicker) {
        datePicker.setValue(appointment.getStart().toLocalDate());
        }

    /**
     * Method used to populate customer ID combobox in update appointment form
     * @param appointment Parameter used to access customer ID
     * @param comboCustomer Parameter used to populate customer ID combobox in update appointment form
     * @throws Exception For throwing Exception
     */
    public static void apptCustomerComboFiltering(Appointment appointment, ComboBox<Customer> comboCustomer) throws Exception {
        for (Customer c : CustomerDB.getAllCustomers()) {
            if (c.getCustomerId() == appointment.getCustomerId()) {
                comboCustomer.setValue(c);
            }
        }
    }

    /**
     * Method used to populate user ID combobox in update appointment form
     * @param appointment Parameter used to access user ID
     * @param comboUser Parameter used to populate user ID combobox in
     *                  update appointment form
     */
    public static void apptUserComboFiltering(Appointment appointment, ComboBox<User> comboUser) {
        for (User u : ListManager.allUsers) {
            if (u.getUserId() == appointment.getUserId()) {
                comboUser.setValue(u);
            }
        }
    }

    /**
     * Method used to populate months observable list for use in second report
     * in reports screen
     */
    public static void reportMonthComboFiltering() {
        ListManager.allMonths.addAll("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }
}


