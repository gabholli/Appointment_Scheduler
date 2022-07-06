package helper;

import database.AppointmentDB;
import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.util.Objects;

public class Search {
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

    public static ObservableList<Appointment> lookupAppointmentID(int appointmentId) throws Exception {

        ObservableList<Appointment> idAppointment = FXCollections.observableArrayList();

        ObservableList<Appointment> allAppointmentId = AppointmentDB.getAllAppointments();

        for (Appointment a : allAppointmentId) {
            if (a.getApptId() == appointmentId) {
                idAppointment.add(a);
            }
        }

        return idAppointment;
    }
}
