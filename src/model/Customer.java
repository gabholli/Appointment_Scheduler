package model;

/**
 * Class for customer data structure
 */
public class Customer {

    /**
     * Field for customer ID
     */
    private int customerId;

    /**
     * Field for customer name
     */
    private String customerName;

    /**
     * Field for customer address
     */
    private String address;

    /**
     * Field for customer postal code
     */
    private String postalCode;

    /**
     * Field for customer phone number
     */
    private String phoneNumber;

    /**
     * Field for country name
     */
    private String countryName;

    /**
     * Field for first level division name
     */
    private String divisionName;

    /**
     * Field for country ID
     */
    private int countryId;

    /**
     * Field for division ID
     */
    private int divisionId;


    /**
     * Constructor for Customer class
     * @param customerId The customer ID
     * @param customerName The customer name
     * @param address The customer address
     * @param postalCode The customer postal code
     * @param phoneNumber The customer phone number
     * @param countryName The country name
     * @param divisionName The division name
     * @param countryId The country ID
     * @param divisionId The division ID
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber,
                    String countryName, String divisionName, int countryId, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.countryName = countryName;
        this.divisionName = divisionName;
        this.countryId = countryId;
        this.divisionId = divisionId;
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
     * Getter for customer name
     * @return The customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for customer name, never used
     * @param customerName The customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for customer address
     * @return The customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for customer address, never used
     * @param address The customer address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for customer postal code
     * @return The customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for customer postal code, never used
     * @param postalCode The customer postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for customer phone number
     * @return The customer phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for customer phone number, never used
     * @param phoneNumber The customer phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter for country name, never used
     * @param countryName The country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Setter for division name, never used
     * @param divisionName THe division name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Getter for country ID
     * @return The country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Setter for country ID, never used
     * @param countryId The country ID
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Getter for division ID
     * @return The division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Setter for division ID, never used
     * @param divisionId The division ID
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Getter for division name, never used
     * @return The division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Getter for country name, never used
     * @return The country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Method used to return string representation
     * @return The string value of customer ID
     */
    @Override
    public String toString() {
        return String.valueOf(customerId);
    }

}
