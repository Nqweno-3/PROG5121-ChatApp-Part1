/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

/**
 *
 * Handles user registration, validation and login.
 *
 * @author Student
 */
public class Login {

    // Registration details
    private final String firstName;
    private final String lastName;
    private final String username;
    private String password;
    private String phoneNumber;

    // Login details
    private String enteredUsername;
    private String enteredPassword;

    // Constructor
    public Login(String firstName, String lastName,
                 String username, String password,
                 String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Password
    public void setPassword(String password) {
        this.password = password;
    }

    // Cellphone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Return registered username
    public String getUsername() {
        return username;
    }

    // Set username entered during login
    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    // Set password entered during login
    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    // Set both login details
    public void setLoginDetails(String enteredUsername,
                                String enteredPassword) {

        this.enteredUsername = enteredUsername;
        this.enteredPassword = enteredPassword;
    }

    // Username must contain an underscore
    // and must not exceed five characters
    public boolean checkUserName() {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // Password complexity validation
    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasUppercase
                && hasNumber
                && hasSpecialCharacter;
    }

    // Validate South African cellphone number
    // using a regular expression
    //
    // Reference:
    // https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/regex/Pattern.html
    public boolean checkCellPhoneNumber() {

        return phoneNumber != null
                && phoneNumber.matches("\\+27[0-9]{9}");
    }

    // Register user and return validation messages
    public String registerUser() {

        StringBuilder result = new StringBuilder();

        // Username validation
        if (checkUserName()) {

            result.append(
                    "Username successfully captured.\n");

        } else {

            result.append(
                    "Username is not correctly formatted; "
                    + "please ensure that your username "
                    + "contains an underscore and is no more "
                    + "than five characters in length.\n");
        }

        // Password validation
        if (checkPasswordComplexity()) {

            result.append(
                    "Password successfully captured.\n");

        } else {

            result.append(
                    "Password is not correctly formatted; "
                    + "please ensure that the password "
                    + "contains at least eight characters, "
                    + "a capital letter, a number, "
                    + "and a special character.\n");
        }

        // Cellphone number validation
        if (checkCellPhoneNumber()) {

            result.append(
                    "Cell phone number successfully added.");

        } else {

            result.append(
                    "Cell phone number incorrectly formatted "
                    + "or does not contain international code.");
        }

        return result.toString();
    }

    // Authenticate registered user
    public boolean loginUser() {

        return username != null
                && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    // Return login status
    public String returnLoginStatus() {

        if (loginUser()) {

            return "Welcome " + firstName + " "
                    + lastName
                    + ", it is great to see you again.";

        } else {

            return "Username or password incorrect, "
                    + "please try again.";
        }
    }
}
