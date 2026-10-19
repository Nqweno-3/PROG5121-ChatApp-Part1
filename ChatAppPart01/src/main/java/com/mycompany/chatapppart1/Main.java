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
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // 1. Register the user
        System.out.println("Please register below.");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter your username: ");
        String username = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Enter your cellphone number: ");
        String phoneNumber = input.nextLine();

        // Create the Login object using your existing constructor
        Login login = new Login(
                firstName, lastName, username, password, phoneNumber
        );

        // Display registration results
        System.out.println("\n" + login.registerUser());

        // Check whether registration details are valid
        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {

            System.out.println("\nRegistration unsuccessful. Please restart and enter valid details.");
            input.close();
            return;
        }

        System.out.println("\nRegistration completed successfully.");

        // 2. Login
        System.out.println("\nPlease log in.");

        System.out.print("Enter your username: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = input.nextLine();

        login.setEnteredUsername(enteredUsername);
        login.setEnteredPassword(enteredPassword);

        // Display login status
        System.out.println(login.returnLoginStatus());

        // Continue only if login succeeds
        if (!login.loginUser()) {
            input.close();
            return;
        }

        // 3. QuickChat menu
        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;

        while (running) {

            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");

            String menuChoice = input.nextLine();

            switch (menuChoice) {

                case "1" ->  {
                    // Ask how many messages the user wants to process
                    System.out.print("How many messages would you like to send? ");

                    int messageLimit = 0;

                    try {
                        messageLimit = Integer.parseInt(input.nextLine());

                        if (messageLimit <= 0) {
                            System.out.println("Please enter a number greater than zero.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Please enter a whole number.");
                    }

                    // Loop through the requested number of messages
                    for (int i = 0; i < messageLimit; i++) {

                        System.out.println("\nMessage " + (i + 1));

                        System.out.print("Enter recipient cellphone number: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = input.nextLine();

                        // Create a new message
                        Message currentMessage =
                                new Message(recipient, messageText, i);

                        // Validate message ID
                        if (!currentMessage.checkMessageID()) {
                            System.out.println("Invalid message ID.");
                            continue;
                        }

                        // Validate recipient
                        String recipientResult =
                                currentMessage.checkRecipientCell();

                        System.out.println(recipientResult);

                        if (!recipientResult.equals(
                                "Cell phone number successfully captured.")) {
                            continue;
                        }

                        // Validate message length
                        String lengthResult =
                                currentMessage.checkMessageLength();

                        System.out.println(lengthResult);

                        if (!lengthResult.equals("Message ready to send.")) {
                            continue;
                        }

                        // Display message hash
                        System.out.println(
                                "Message Hash: "
                                + currentMessage.getMessageHash()
                        );

                        // Choose an action
                        System.out.println("\nChoose an action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Enter your choice: ");

                        String messageChoice = input.nextLine();

                        String result;

                        switch (messageChoice) {

                            case "1" -> {
                                result = currentMessage.SentMessage("Send Message");
                                System.out.println(result);

                                // Display details only after successful sending
                                if (currentMessage.getStatus().equals("SENT")) {

                                    System.out.println(
                                            "Message ID: "
                                                    + currentMessage.getMessageID()
                                    );

                                    System.out.println(
                                            "Message Hash: "
                                                    + currentMessage.getMessageHash()
                                    );

                                    System.out.println(
                                            "Recipient: "
                                                    + currentMessage.getRecipient()
                                    );

                                    System.out.println(
                                            "Message: "
                                                    + currentMessage.getMessage()
                                    );
                                }
                            }

                            case "2" -> {
                                result = currentMessage.SentMessage(
                                        "Disregard Message"
                                );
                                System.out.println(result);
                            }

                            case "3" -> {
                                result = currentMessage.SentMessage(
                                        "Store Message"
                                );
                                System.out.println(result);
                            }

                            default -> System.out.println(
                                        "Invalid choice. Please choose 1, 2 or 3."
                                );
                        }
                    }

                    // Display the total number of messages sent
                    Message counter = new Message("", "", 0);

                    System.out.println(
                            "\nTotal messages sent: "
                            + counter.returnTotalMessagess()
                    );

                }

                case "2" -> // Required behaviour in the assignment instructions
                    System.out.println("Coming Soon.");

                case "3" -> {
                    System.out.println("Thank you for using QuickChat.");
                    running = false;
                }

                default -> System.out.println(
                            "Invalid option. Please choose 1, 2 or 3."
                    );
            }
        }

        input.close();
    }
}



    