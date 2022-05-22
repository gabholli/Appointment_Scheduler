package model;

/**
 * Class for division data structure
 */
public class Division {

    /**
     * Field for division name
     */
    private String division;

    /**
     * Field for division ID
     */
    private int divisionId;

    /**
     * Field for country ID
     */
    private int countryId;

    /**
     * Constructor for Division class
     * @param divisionId The division ID
     * @param division The division name
     * @param countryId The country ID
     */
    public Division(int divisionId, String division, int countryId) {
        this.division = division;
        this.divisionId = divisionId;
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
     * Setter for division ID
     * @param divisionId The division ID
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
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
     * Getter for division name, never used
     * @return The division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * Setter for division ID, never used
     * @param division The division name
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Method used to return string representation of division name
     * @return The division name
     */
    @Override
    public String toString() {
        return division;
    }

}
