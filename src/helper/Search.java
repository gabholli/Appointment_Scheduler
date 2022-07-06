package helper;

import database.AppointmentDB;
import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.util.Objects;

public class Search {

    public static Customer lookupCustomer(int customerID) throws Exception {

        for (Customer c : CustomerDB.getAllCustomers()) {
            if (c.getCustomerId() == customerID) {
                return c;
            }
        }
        return null;
    }

    public static ObservableList<Customer> lookupCustomer(String customerName) throws Exception {

        ObservableList<Customer> nameCustomer = FXCollections.observableArrayList();

        ObservableList<Customer> allCustomers = CustomerDB.getAllCustomers();

        for (Customer c : allCustomers) {
            if (c.getCustomerName().contains(customerName)) {
                nameCustomer.add(c);
            }
        }

        if (Objects.equals(customerName, "")) {
            Dialogs.failedSearchPrompt();
        }

        return nameCustomer;
    }

    public static Appointment lookupAppointment(int appointmentId) throws Exception {

        for (Appointment a : AppointmentDB.getAllAppointments()) {
            if (a.getApptId() == appointmentId) {
                return a;
            }
        }
        return null;
    }

    public static ObservableList<Appointment> lookupAppointment(String appointmentTitle) throws Exception {

        ObservableList<Appointment> titleAppointment = FXCollections.observableArrayList();

        ObservableList<Appointment> allAppointments = AppointmentDB.getAllAppointments();

        for (Appointment a : allAppointments) {
            if (a.getTitle().contains(appointmentTitle)) {
                titleAppointment.add(a);
            }
        }

        if (Objects.equals(appointmentTitle, "")) {
            Dialogs.failedSearchPrompt();
        }
        return titleAppointment;
    }
}
