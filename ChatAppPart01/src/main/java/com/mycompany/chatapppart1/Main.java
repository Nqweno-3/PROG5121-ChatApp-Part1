/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.chatapppart1;

import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create Message Manager
        MessageManager manager = new MessageManager();

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

        System.out.println("\nWelcome to QuickChat.");

        // 3. Set message allowance
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
                        "ERROR: Please enter a number greater than zero."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERROR: Invalid number. Please enter a whole number."
                );
            }
        }

        int messagesProcessed = 0;

        // 4. QuickChat menu
        boolean running = true;

        while (running) {

            System.out.println("\n========== QUICKCHAT MENU ==========");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Search message by ID");
            System.out.println("4) Search messages by recipient");
            System.out.println("5) Delete message by hash");
            System.out.println("6) Display message report");
            System.out.println("7) Quit");

            System.out.print("Enter your choice: ");

            String menuChoice = input.nextLine().trim();

System.out.println("DEBUG: You selected [" + menuChoice + "]");

switch (menuChoice) {
                case "1":

                    if (messagesProcessed >= messageLimit) {

                        System.out.println(
                                "ERROR: Message limit reached."
                        );
                        break;
                    }

                    while (messagesProcessed < messageLimit) {

                        int messageNumber = messagesProcessed;

                        System.out.println(
                                "\nCreating message "
                                + (messageNumber + 1)
                                + " of " + messageLimit
                        );

                        String recipient;
                        String messageText;
                        Message currentMessage;

                        // Validate recipient immediately
                        while (true) {

                            System.out.print(
                                    "Enter recipient cellphone number: "
                            );

                            recipient = input.nextLine();

                            currentMessage = new Message(
                                    recipient, "", messageNumber
                            );

                            String recipientResult =
                                    currentMessage.checkRecipientCell();

                            if (!recipientResult.equals(
                                    "Cell phone number successfully captured.")) {

                                System.out.println(
                                        "\nERROR: " + recipientResult
                                );

                                System.out.println(
                                        "Please enter a valid number "
                                        + "using international format."
                                );

                            } else {

                                System.out.println(recipientResult);
                                break;
                            }
                        }

                        // Validate message length immediately
                        while (true) {

                            System.out.print("Enter your message: ");
                            messageText = input.nextLine();

                            currentMessage = new Message(
                                    recipient, messageText, messageNumber
                            );

                            String lengthResult =
                                    currentMessage.checkMessageLength();

                            if (!lengthResult.equals(
                                    "Message ready to send.")) {

                                System.out.println(
                                        "\nERROR: " + lengthResult
                                );

                                System.out.println(
                                        "Please enter your message again."
                                );

                            } else {

                                System.out.println(lengthResult);
                                break;
                            }
                        }

                        // Validate message ID
                        if (!currentMessage.checkMessageID()) {

                            System.out.println(
                                    "ERROR: Invalid message ID. "
                                    + "Please create the message again."
                            );

                            continue;
                        }

                        // Display generated hash
                        System.out.println(
                                "Message Hash: "
                                + currentMessage.getMessageHash()
                        );

                        // Choose message action
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
                                        "ERROR: Invalid choice. "
                                        + "Please select 1, 2 or 3."
                                );

                                continue;
                        }

                        // Count one completed message attempt
                        messagesProcessed++;

                        System.out.println(
                                "\nMessages processed: "
                                + messagesProcessed
                                + " of " + messageLimit
                        );
                    }

                    System.out.println("\nMessage allowance completed.");

                    System.out.println(
                            "Total messages successfully sent: "
                            + new Message("", "", 0)
                                    .returnTotalMessagess()
                    );

                    break;

                case "2":

                    System.out.println("\nRecently Sent Messages");
                    manager.displayMessages();
                    break;

                case "3":

                    System.out.print("Enter the message ID: ");
                    String searchID = input.nextLine();

                    Message foundMessage =
                            manager.searchMessage(searchID);

                    if (foundMessage != null) {

                        System.out.println("\nMessage found!");

                        System.out.println(
                                "Recipient: "
                                + foundMessage.getRecipient()
                        );

                        System.out.println(
                                "Message: "
                                + foundMessage.getMessage()
                        );

                        System.out.println(
                                "Message Hash: "
                                + foundMessage.getMessageHash()
                        );

                    } else {

                        System.out.println(
                                "ERROR: Message not found."
                        );
                    }

                    break;

                case "4":

                    System.out.print(
                            "Enter recipient cellphone number: "
                    );

                    String searchRecipient = input.nextLine();

                    List<Message> matchingMessages =
                            manager.searchByRecipient(searchRecipient);

                    if (matchingMessages.isEmpty()) {

                        System.out.println("No messages found.");

                    } else {

                        System.out.println("\nMessages found:");

                        for (Message message : matchingMessages) {

                            System.out.println(
                                    "Message ID: "
                                    + message.getMessageID()
                            );

                            System.out.println(
                                    "Message: "
                                    + message.getMessage()
                            );

                            System.out.println("--------------------");
                        }
                    }

                    break;

                case "5":

                    System.out.print("Enter the message hash: ");
                    String deleteHash = input.nextLine();

                    if (manager.deleteMessage(deleteHash)) {

                        System.out.println(
                                "Message successfully deleted."
                        );

                    } else {

                        System.out.println(
                                "ERROR: Message not found. "
                                + "Unable to delete."
                        );
                    }

                    break;

                case "6":

                    System.out.println("\nMessage Report");
                    manager.displayReport();
                    break;

                case "7":

                    System.out.println(
                            "Thank you for using QuickChat."
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "ERROR: Invalid option. "
                            + "Please choose between 1 and 7."
                    );

                    break;
            }
        }

        input.close();
    }
}

