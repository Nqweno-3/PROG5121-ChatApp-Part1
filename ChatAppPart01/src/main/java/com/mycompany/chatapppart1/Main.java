/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Scanner;

/**
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

        Login login = new Login(
                firstName, lastName, username, password, phoneNumber
        );

        System.out.println("\n" + login.registerUser());

        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {

            System.out.println(
                    "\nRegistration unsuccessful. Please restart "
                    + "and enter valid details."
            );

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

        System.out.println(login.returnLoginStatus());

        if (!login.loginUser()) {
            input.close();
            return;
        }

        // 3. Ask for the message allowance once
        int messageLimit;

        while (true) {
            System.out.print(
                    "\nEnter the number of messages you wish to send: "
            );

            try {
                messageLimit = Integer.parseInt(input.nextLine());

                if (messageLimit > 0) {
                    break;
                }

                System.out.println(
                        "Please enter a number greater than zero."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid number. Please enter a whole number."
                );
            }
        }

        int messagesProcessed = 0;

        // 4. ChatApp menu
        System.out.println("\nWelcome to ChatApp.");

        boolean running = true;

        while (running) {

            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");

            String menuChoice = input.nextLine();

            switch (menuChoice) {

                case "1":

                    if (messagesProcessed >= messageLimit) {
                        System.out.println("Message limit reached.");
                        break;
                    }

                    // The for loop processes only the remaining allowance.
                    for (int i = messagesProcessed;
                         i < messageLimit; i++) {

                        System.out.println(
                                "\nCreating message " + (i + 1)
                                + " of " + messageLimit
                        );

                        System.out.print(
                                "Enter recipient cellphone number: "
                        );
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = input.nextLine();

                        Message currentMessage =
                                new Message(recipient, messageText, i);

                        // Each entry consumes one attempt.
                        messagesProcessed++;

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

                        // Display generated hash
                        System.out.println(
                                "Message Hash: "
                                + currentMessage.getMessageHash()
                        );

                        // Choose what happens to the message
                        System.out.println("\nChoose an action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Enter your choice: ");

                        String messageChoice = input.nextLine();
                        String result;

                        switch (messageChoice) {

                            case "1":
                                result = currentMessage.SentMessage(
                                        "Send Message"
                                );

                                System.out.println(result);

                                if ("SENT".equals(
                                        currentMessage.getStatus())) {

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
                                break;

                            case "2":
                                result = currentMessage.SentMessage(
                                        "Disregard Message"
                                );
                                System.out.println(result);
                                break;

                            case "3":
                                result = currentMessage.SentMessage(
                                        "Store Message"
                                );
                                System.out.println(result);
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Message not processed."
                                );
                                break;
                        }
                    }

                    System.out.println("\nMessage limit reached.");

                    System.out.println(
                            "Total messages successfully sent: "
                            + new Message("", "", 0)
                                    .returnTotalMessagess()
                    );

                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    System.out.println("Thank you for using ChatApp.");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1, 2 or 3."
                    );
                    break;
            }
        }

        input.close();
    }
}

    
