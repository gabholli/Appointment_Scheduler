package model;

import java.time.LocalDateTime;

/**
 * Class used for appointment data structure
 */
public class Appointment {
    /**
     * Field for appointment ID
     */
    private int apptId;
    /**
     * Field for appointment title
     */
    private String title;
    /**
     * Field for appointment description
     */
    private String description;
    /**
     * Field for appointment location
     */
    private String location;
    /**
     * Field for contact ID
     */
    private int contactId;
    /**
     * Field for contact name
     */
    private String contactName;
    /**
     * Field for appointment type
     */
    private String type;
    /**
     * Field for appointment start time
     */
    private LocalDateTime start;
    /**
     * Field for appointment end time
     */
    private LocalDateTime end;
    /**
     * Field for customer ID
     */
    private int customerId;
    /**
     * Field for user ID
     */
    private int userId;

    /**
     * Constructor for Appointment class
     * @param apptId The appointment ID
     * @param title The title
     * @param description The description
     * @param location The location
     * @param contactId The contact ID
     * @param contactName The contact name
     * @param type The type
     * @param start The starting time
     * @param end The ending time
     * @param customerId The customer ID
     * @param userId The user ID
     */
    public Appointment(int apptId, String title, String description, String location, int contactId, String contactName,
                       String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) {
        this.apptId = apptId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.contactName = contactName;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * Getter for appointment ID
     * @return The appointment ID
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * Setter for appointment ID, never used
     * @param apptId The appointment ID
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * Getter for appointment title
     * @return The appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for appointment title
     * @param title The appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for appointment description
     * @return The appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for appointment description, never used
     * @param description The appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for appointment location
     * @return The appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for appointment location, never used
     * @param location The appointment location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for contact ID, never used
     * @return The contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Setter for contact ID, never used
     * @param contactId The contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Getter for appointment type
     * @return The appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for appointment type
     * @param type The appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for appointment starting time
     * @return The appointment starting time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for appointment starting time
     * @param start The appointment starting time
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for appointment ending time
     * @return The appointment ending time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for appointment ending time
     * @param end The appointment ending time
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for customer ID
     * @return The customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customer ID, never used
     * @param customerId The customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for contact name
     * @return The contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for contact name, never used
     * @param contactName The contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter for user ID
     * @return The user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for user ID
     * @param userId The user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Method used to return a string representation
     * @return The string value of the customer ID
     */
    @Override
    public String toString() {
        return String.valueOf(customerId);
    }


}
