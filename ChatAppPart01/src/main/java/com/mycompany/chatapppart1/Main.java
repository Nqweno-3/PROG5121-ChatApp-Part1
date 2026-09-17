/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 * to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Scanner;

/**
 * The Main class starts and controls the ChatApp program.
 * It allows the user to enter information and complete
 * the registration and login process.
 *
 * @author Student
 */
public class Main {

    /*
     * The main method is where the program begins running.
     */
    public static void main(String[] args) {

        //Scanner is used to receive information entered by the user.
        Scanner input = new Scanner(System.in);

        //Show the registration section to the user.
        System.out.println("=====REGISTRATION=====");

        //Request the user's first name.
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        //Make sure the first name is not left blank.
        while (firstName.trim().isEmpty()) {
            System.out.println("First name cannot be empty.");
            System.out.print("Enter your first name: ");
            firstName = input.nextLine();
        }

        //Request the user's last name.
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        //Prevent the user from continuing without entering a last name.
        while (lastName.trim().isEmpty()) {
            System.out.println("Last name cannot be empty.");
            System.out.print("Enter your last name: ");
            lastName = input.nextLine();
        }

        //Ask the user to provide a username.
        System.out.print("Enter your username: ");
        String username = input.nextLine();

        //Check that the username field has not been left empty.
        while (username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            System.out.print("Enter your username: ");
            username = input.nextLine();
        }

        /*
         * Create the Login object with the details collected so far.
         * The password and cellphone number are left empty for now
         * because they will be entered later.
         */
        Login login = new Login(
                firstName,
                lastName,
                username,
                "",
                ""
        );

        //Validate the username before moving on to the password.
        if (!login.checkUserName()) {
            System.out.println(
                    "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length."
            );
            return;
        }

        //Ask the user to create a password.
        System.out.print("Enter your password: ");
        String password = input.nextLine();

        //Make sure a password has been entered.
        while (password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            System.out.print("Enter your password: ");
            password = input.nextLine();
        }

        //Save the password in the Login object.
        login.setPassword(password);

        //Check the password before moving to the cellphone number.
        if (!login.checkPasswordComplexity()) {
            System.out.println(
                    "Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number, "
                    + "and a special character."
            );
            return;
        }

        //Ask the user to enter their cellphone number.
        System.out.print("Enter your cellphone number: ");
        String phoneNumber = input.nextLine();

        //Make sure the cellphone number field is not empty.
        while (phoneNumber.trim().isEmpty()) {
            System.out.println("Cellphone number cannot be empty.");
            System.out.print("Enter your cellphone number: ");
            phoneNumber = input.nextLine();
        }

        //Save the cellphone number in the Login object.
        login.setPhoneNumber(phoneNumber);

        //Validate the cellphone number before starting the login process.
        if (!login.checkCellPhoneNumber()) {
            System.out.println(
                    "Cellphone number is not correctly formatted; please ensure that the "
                    + "cellphone number starts with +27 and is followed by 9 numbers."
            );
            return;
        }

        /*
         * At this point, all the registration checks have passed.
         * Display the messages confirming the successful registration.
         */
        System.out.println();
        System.out.println(login.registerUser());

        //Show the login section.
        System.out.println();
        System.out.println("=====LOGIN=====");

        //Ask the user to enter their registered username.
        System.out.print("Enter your username: ");
        String enteredUsername = input.nextLine();

        //Make sure the username is not left blank.
        while (enteredUsername.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            System.out.print("Enter your username: ");
            enteredUsername = input.nextLine();
        }

        //Check that the entered username is the same as the registered one.
        if (!enteredUsername.equals(login.getUsername())) {
            System.out.println("Username or password incorrect, please try again.");
            return;
        }

        //Ask the user to enter their registered password.
        System.out.print("Enter your password: ");
        String enteredPassword = input.nextLine();

        //Make sure the password field is not empty.
        while (enteredPassword.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            System.out.print("Enter your password: ");
            enteredPassword = input.nextLine();
        }

        //Save the username and password entered during the login attempt.
        login.setEnteredUsername(enteredUsername);
        login.setEnteredPassword(enteredPassword);

        //Display the result of the login attempt.
        System.out.println(login.returnLoginStatus());
    }
}
