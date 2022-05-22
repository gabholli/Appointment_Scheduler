package model;

/**
 * Class for country data structure
 */
public class Country {

    /**
     * Field for country ID
     */
    private int countryId;

    /**
     * field for country name
     */
    private String country;

    /**
     * Constructor for Country class
     * @param countryId The country ID
     * @param country The country name
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Getter for country name
     * @return The country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for country name
     * @param country The country name
     */
    public void setCountry(String country) {
        this.country = country;
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
     * Method used to return a string representation of country name
     * @return
     */
    @Override
    public String toString() {
        return country;
    }

}
