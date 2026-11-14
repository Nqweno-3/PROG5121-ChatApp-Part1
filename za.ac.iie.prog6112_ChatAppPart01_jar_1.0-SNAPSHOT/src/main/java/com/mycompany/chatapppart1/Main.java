/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.chatapppart1;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        MessageManager manager = new MessageManager();

        // Load stored messages from JSON.
        if (!Message.loadStoredMessages()) {
            System.out.println(
                    "Warning: Stored messages could not be loaded.");
        }

        System.out.println("Welcome to QuickChat.");
        System.out.println("Please register below.");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine().trim();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine().trim();

        System.out.print("Enter your username: ");
        String username = input.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Enter your cellphone number: ");
        String phoneNumber = input.nextLine().trim();

        Login login = new Login(
                firstName,
                lastName,
                username,
                password,
                phoneNumber
        );

        System.out.println("\n" + login.registerUser());

        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {

            System.out.println(
                    "Registration unsuccessful. "
                    + "Please restart and enter valid details.");
            return;
        }

        System.out.println("\nPlease log in.");

        System.out.print("Enter your username: ");
        login.setEnteredUsername(input.nextLine().trim());

        System.out.print("Enter your password: ");
        login.setEnteredPassword(input.nextLine());

        System.out.println(login.returnLoginStatus());

        if (!login.loginUser()) {
            return;
        }

        String sender = firstName + " " + lastName;

        int messageLimit = readPositiveInteger(
                "Enter the number of messages you wish to send: "
        );

        int messagesProcessed = 0;
        boolean running = true;

        // Main menu uses a while loop.
        while (running) {

            System.out.println(
                    "\n========== QUICKCHAT MENU ==========");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Display total messages sent");
            System.out.println("4) Stored Messages");
            System.out.println("5) Quit");

            String choice = readChoice("Enter your choice: ");

            switch (choice) {

                case "1":

                    if (messagesProcessed >= messageLimit) {
                        System.out.println("Message limit reached.");
                        break;
                    }

                    // Process messages using a for loop.
                    for (int i = messagesProcessed;
                            i < messageLimit; i++) {

                        System.out.println(
                                "\nCreating message "
                                + (i + 1)
                                + " of "
                                + messageLimit
                        );

                        String recipient;

                        // Validate the recipient before creating
                        // the actual message.
                        while (true) {

                            recipient = readChoice(
                                    "Enter recipient cellphone number: "
                            );

                            if (recipient.matches(
                                    "\\+27[6-8][0-9]{8}")) {
                                break;
                            }

                            System.out.println(
                                    "Cell phone number is incorrectly "
                                    + "formatted or does not contain "
                                    + "an international code. "
                                    + "Please try again."
                            );
                        }

                        String messageText;

                        // Validate the message length.
                        while (true) {

                            System.out.print("Enter your message: ");
                            messageText = input.nextLine();

                            if (!messageText.trim().isEmpty()
                                    && messageText.length() <= 250) {
                                break;
                            }

                            if (messageText.trim().isEmpty()) {

                                System.out.println(
                                        "Please enter a message.");

                            } else {

                                System.out.println(
                                        "Message exceeds 250 characters by "
                                        + (messageText.length() - 250)
                                        + "; please reduce the size.");
                            }
                        }

                        Message currentMessage = new Message(
                                recipient,
                                messageText,
                                i
                        );

                        boolean actionCompleted = false;

                        while (!actionCompleted) {

                            System.out.println(
                                    "\nChoose a message action:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message");

                            String action = readChoice(
                                    "Enter your choice: "
                            );

                            switch (action) {

                                case "1":

                                    System.out.println(
                                            currentMessage.SentMessage(
                                                    "Send")
                                    );

                                    if ("SENT".equals(
                                            currentMessage.getStatus())) {

                                        printMessage(currentMessage);
                                        actionCompleted = true;
                                    }
                                    break;

                                case "2":

                                    System.out.println(
                                            currentMessage.SentMessage(
                                                    "Disregard")
                                    );

                                    String confirmation = readChoice(
                                            "Enter 0 to confirm: "
                                    );

                                    if (currentMessage.confirmDisregard(
                                            confirmation)) {

                                        System.out.println(
                                                "Message disregarded.");
                                        actionCompleted = true;

                                    } else {

                                        System.out.println(
                                                "Deletion cancelled. "
                                                + "Choose another action.");
                                    }
                                    break;

                                case "3":

                                    System.out.println(
                                            currentMessage.SentMessage(
                                                    "Store")
                                    );

                                    if ("STORED".equals(
                                            currentMessage.getStatus())) {

                                        actionCompleted = true;
                                    }
                                    break;

                                default:

                                    System.out.println(
                                            "Invalid message option. "
                                            + "Please try again.");
                            }
                        }

                        messagesProcessed++;
                    }

                    System.out.println(
                            "\nTotal messages successfully sent: "
                            + Message.getTotalMessagesSent()
                    );
                    break;

                case "2":

                    manager.displayMessages();
                    break;

                case "3":

                    System.out.println(
                            "Total messages successfully sent: "
                            + Message.getTotalMessagesSent()
                    );
                    break;

                case "4":

                    storedMessagesMenu(manager, sender);
                    break;

                case "5":

                    System.out.println(
                            "Thank you for using QuickChat.");
                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please choose 1 to 5.");
            }
        }

        input.close();
    }

    /**
     * Stored Messages menu.
     */
    private static void storedMessagesMenu(
            MessageManager manager,
            String sender) {

        boolean inMenu = true;

        while (inMenu) {

            System.out.println(
                    "\n========== STORED MESSAGES ==========");
            System.out.println("1) Display stored messages");
            System.out.println("2) Display sender and recipient");
            System.out.println("3) Display longest stored message");
            System.out.println("4) Search message by ID");
            System.out.println("5) Search messages by recipient");
            System.out.println("6) Delete message by hash");
            System.out.println("7) Display stored message report");
            System.out.println("8) Return to main menu");

            String choice = readChoice("Enter your choice: ");

            switch (choice) {

                case "1":

                    manager.displayStoredMessages();
                    break;

                case "2":

                    manager.displaySenderAndRecipient(sender);
                    break;

                case "3":

                    manager.displayLongestMessage();
                    break;

                case "4":

                    String id = readChoice(
                            "Enter message ID: "
                    );

                    Message found = manager.searchMessage(id);

                    if (found == null) {

                        System.out.println("Message not found.");

                    } else {

                        System.out.println("\nMessage found:");

                        System.out.println(
                                "Recipient: " + found.getRecipient());

                        System.out.println(
                                "Message: " + found.getMessage());
                    }
                    break;

                case "5":

                    String recipient = readChoice(
                            "Enter recipient cellphone number: "
                    );

                    List<Message> matches =
                            manager.searchByRecipient(recipient);

                    if (matches.isEmpty()) {

                        System.out.println("No messages found.");

                    } else {

                        for (Message message : matches) {
                            printMessage(message);
                        }
                    }
                    break;

                case "6":

                    String hash = readChoice(
                            "Enter message hash: "
                    );

                    if (manager.deleteMessage(hash)) {

                        System.out.println(
                                "Message successfully deleted.");

                    } else {

                        System.out.println(
                                "Message not found. Unable to delete.");
                    }
                    break;

                case "7":

                    manager.displayReport();
                    break;

                case "8":

                    inMenu = false;
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please choose 1 to 8.");
            }
        }
    }

    /**
     * Display each message's details once.
     */
    private static void printMessage(Message message) {

        System.out.println(
                "Message ID: " + message.getMessageID());

        System.out.println(
                "Message Hash: " + message.getMessageHash());

        System.out.println(
                "Recipient: " + message.getRecipient());

        System.out.println(
                "Message: " + message.getMessage());

        System.out.println(
                "Status: " + message.getStatus());
    }

    private static String readChoice(String prompt) {

        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private static int readPositiveInteger(String prompt) {

        while (true) {

            System.out.print(prompt);

            try {

                int number = Integer.parseInt(
                        input.nextLine().trim()
                );

                if (number > 0) {
                    return number;
                }

                System.out.println(
                        "Please enter a number greater than zero.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid whole number.");
            }
        }
    }
}
