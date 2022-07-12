package model;

import helper.ListManager;

/**
 * Class for user data structure
 */
public class User {

    /**
     * Field for user ID
     */
    private int userId;

    /**
     * Field for username
     */
    private String userName;

    /**
     * Field for password
     */
    private String password;

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Constructor for User class
     * @param userId The user ID
     */
    public User(int userId) {
        this.userId = userId;
    }

    public User() {

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
     * Getter for username, never used
     * @return The user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for username, never used
     * @param userName The username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for password, never used
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password, never used
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method used to return string representation
     * @return The string value of user ID
     */
    @Override
    public String toString() {
        return String.valueOf(userId);
    }

    /**
     * Method used to display message in main screen label if no radio button selected in login screen
     * @return Returns indicated string
     */
    public String message() {
        return "No User Type Selected";
    }

}
