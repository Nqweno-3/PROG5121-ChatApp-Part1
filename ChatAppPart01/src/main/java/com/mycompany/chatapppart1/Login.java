/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 */
package com.mycompany.chatapppart1;

/**
 * The Login class is responsible for handling user registration
 * and login functions in the ChatApp application.
 *
 * @author Student
 */
public class Login {

    // Stores the details provided by the user when registering
    private String username;
    private String password;
    private String phoneNumber;

    // Stores the username and password entered when logging in
    private String enteredUsername;
    private String enteredPassword;

    // Keeps track of the user's first and last name
    private String firstName;
    private String lastName;

    /*
     * Creates a Login object and saves the registration details
     * provided by the user.
     */
    public Login(String firstName, String lastName, String username,
            String password, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    /*
     * Updates the password stored for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * Updates the cellphone number stored for the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /*
     * Returns the username that was saved during registration.
     */
    public String getUsername() {
        return username;
    }

    /*
     * Saves the username entered by the user when logging in.
     */
    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    /*
     * Saves the password entered by the user when logging in.
     */
    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    /*
     * Checks if the username follows the required format.
     * It needs to have an underscore and cannot be longer than
     * five characters.
     */
    public boolean checkUserName() {

        // The username must contain an underscore and be five
        // characters or less.
        return username.contains("_") && username.length() <= 5;
    }

    /*
     * Checks whether the password follows the required rules.
     *
     * The password needs to:
     * - Be at least eight characters long
     * - Include an uppercase letter
     * - Include a number
     * - Include a special character
     */
    public boolean checkPasswordComplexity() {

        // These variables are used to check each password requirement.
        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        // A password shorter than eight characters is not accepted.
        if (password.length() < 8) {
            return false;
        }

        // Check each character in the password.
        for (int i = 0; i < password.length(); i++) {

            // Gets the character at the current position.
            char character = password.charAt(i);

            // Checks if the character is an uppercase letter.
            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            }

            // Checks if the character is a number.
            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            // Checks if the character is something other than
            // a letter or number.
            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        // The password is accepted only when all requirements
        // have been satisfied.
        if (hasUppercase && hasNumber && hasSpecialCharacter) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Checks whether the cellphone number follows the required
     * South African international format.
     *
     * The number must:
     * - Begin with +27
     * - Have 12 characters altogether
     * - Have only numbers after +27
     */
    public boolean checkCellPhoneNumber() {

        // First check the country code and the total length.
        if (phoneNumber.startsWith("+27")
                && phoneNumber.length() == 12) {

            // Check the remaining characters after +27.
            for (int i = 3; i < phoneNumber.length(); i++) {

                // Every character after +27 must be a number.
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    return false;
                }
            }

            // The cellphone number meets all the requirements.
            return true;

        } else {

            // The cellphone number does not meet the required format.
            return false;
        }
    }

    /*
     * Checks the user's username, password and cellphone number
     * during the registration process and returns the relevant
     * messages.
     */
    public String registerUser() {

    //Used to combine the registration results into one message.
    String message = "";

    //Check whether the username is valid.
    if (checkUserName()) {
        message += "Username successfully captured.\n";
    } else {
        message += "Username is not correctly formatted; "
                + "please ensure that your username contains an "
                + "underscore and is no more than five characters in length.";
    }

    //Check whether the password meets the required rules.
    if (checkPasswordComplexity()) {
        message += "Password successfully captured.\n";
    } else {
        message += "Password is not correctly formatted; "
                + "please ensure that the password contains at least "
                + "eight characters, a capital letter, a number, "
                + "and a special character.";
    }

    //Check whether the cellphone number is valid.
    if (checkCellPhoneNumber()) {
        message += "Cell phone number successfully added.";
    } else {
        message += "Cell phone number incorrectly formatted "
                + "or does not contain international code.";
    }

    //Return the registration results.
    return message;
}

    /*
     * Compares the login details entered by the user with the
     * details that were saved during registration.
     */
    public boolean loginUser() {

        // Both the username and password must match the registered details.
        if (enteredUsername.equals(username)
                && enteredPassword.equals(password)) {

            // The login details are correct.
            return true;

        } else {

            // The username or password does not match.
            return false;
        }
    }

    /*
     * Displays a message based on whether the login was successful.
     */
    public String returnLoginStatus() {

        // Check if the entered login details are correct.
        if (loginUser()) {

            // Display a welcome message when the login succeeds.
            return "Welcome " + firstName + " " + lastName
                    + ", it is great to see you again.";

        } else {

            // Display an error message when the login details are incorrect.
            return "Username or password incorrect, please try again.";
        }
    }
}
