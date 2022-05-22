package model;

/**
 * Class for contact data structure
 */
public class Contacts {

    /**
     * Field for contact ID
     */
    private int contactId;

    /**
     * Field for contact name
     */
    private String contactName;

    /**
     * Constructor for Contacts class
     * @param contactId The contact ID
     * @param contactName The contact name
     */
    public Contacts(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
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
     * Getter for contact ID
     * @return THe contact ID
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
     * Method used to return a string representation of contact name
     * @return The contact name
     */
    @Override
    public String toString() {
        return contactName;
    }

}
