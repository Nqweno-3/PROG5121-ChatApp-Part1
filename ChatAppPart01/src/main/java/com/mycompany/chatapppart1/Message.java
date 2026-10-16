/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Message class handles message creation,
 * validation, sending and storing messages.
 *

/**
 *
 * @author Student
 */
public final class Message {
    
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String message;
    private final String messageHash;
    private String status;

    private static int totalMessagesSent = 0;

    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();

    /*
     * Creates a new message.
     */
    public Message(String recipient, String message, int messageNumber) {

        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
        this.status = "PENDING";
    }

    /*
     * Generates a random ten-digit message ID.
     */
    private String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    /*
     * Checks that the message ID contains ten digits.
     */
    public boolean checkMessageID() {

        return messageID != null
                && messageID.matches("[0-9]{10}");
    }

    /*
     * Validates the recipient's international cellphone number.
     */
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("\\+[1-9][0-9]{7,14}")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or "
                    + "does not contain an international code. "
                    + "Please correct the number and try again.";
        }
    }

    /*
     * Checks whether the message is within the 250-character limit.
     */
    public String checkMessageLength() {

        if (message == null) {
            return "Please enter a message.";
        }

        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = message.length() - 250;

        return "Message exceeds 250 characters by "
                + excess + "; please reduce the size.";
    }

    /*
     * Creates the uppercase message hash.
     */
    public String createMessageHash() {

        if (messageID == null || message == null
                || message.trim().isEmpty()) {

            return "";
        }

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Remove punctuation from the first and last words.
        firstWord = firstWord.replaceAll(
                "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");

        lastWord = lastWord.replaceAll(
                "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");

        String firstTwoCharacters = messageID.substring(0, 2);

        return (firstTwoCharacters + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    /*
     * Allows the user to send, disregard or store a message.
     */
    public String SentMessage(String choice) {

        if (choice == null) {
            return "Invalid message option.";
        }

        if (choice.equalsIgnoreCase("Send Message")
                || choice.equalsIgnoreCase("Send")) {

            if (!"PENDING".equals(status)) {
                return "Message has already been processed.";
            }

            if (!checkMessageID()
                    || !checkRecipientCell().equals(
                            "Cell phone number successfully captured.")
                    || !checkMessageLength().equals(
                            "Message ready to send.")) {

                return "Message could not be sent. Check the message details.";
            }

            status = "SENT";
            sentMessages.add(this);
            totalMessagesSent++;

            return "Message successfully sent.";

        } else if (choice.equalsIgnoreCase("Disregard Message")
                || choice.equalsIgnoreCase("Disregard")
                || choice.equalsIgnoreCase("Discard")) {

            if (!"PENDING".equals(status)) {
                return "Message has already been processed.";
            }

            status = "DISREGARDED";

            return "Press 0 to delete the message.";

        } else if (choice.equalsIgnoreCase("Store Message")
                || choice.equalsIgnoreCase("Store")) {

            if (!"PENDING".equals(status)) {
                return "Message has already been processed.";
            }

            status = "STORED";
            storedMessages.add(this);

            storeMessage();

            return "Message successfully stored.";

        } else {

            return "Invalid message option.";
        }
    }

    /*
     * Returns the details of all messages successfully sent.
     */
    public String printMessages() {

        StringBuilder output = new StringBuilder();

        for (Message sentMessage : sentMessages) {

            output.append("Message ID: ")
                    .append(sentMessage.messageID)
                    .append("\n");

            output.append("Message Hash: ")
                    .append(sentMessage.messageHash)
                    .append("\n");

            output.append("Recipient: ")
                    .append(sentMessage.recipient)
                    .append("\n");

            output.append("Message: ")
                    .append(sentMessage.message)
                    .append("\n\n");
        }

        return output.toString();
    }

    /*
     * Returns the total number of successfully sent messages.
     * The method name follows the assessment spelling.
     */
    public int returnTotalMessagess() {

        return totalMessagesSent;
    }

    /*
     * Stores all messages selected for later sending in JSON.
     */
    public void storeMessage() {

        try (FileWriter writer = new FileWriter("storedMessages.json")) {

            writer.write("[\n");

            for (int i = 0; i < storedMessages.size(); i++) {

                Message stored = storedMessages.get(i);

                writer.write("  {\n");

                writer.write("    \"messageId\": \""
                        + escapeJSON(stored.messageID) + "\",\n");

                writer.write("    \"messageHash\": \""
                        + escapeJSON(stored.messageHash) + "\",\n");

                writer.write("    \"recipient\": \""
                        + escapeJSON(stored.recipient) + "\",\n");

                writer.write("    \"message\": \""
                        + escapeJSON(stored.message) + "\",\n");

                writer.write("    \"status\": \"STORED\"\n");

                writer.write("  }");

                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]\n");

        } catch (IOException e) {

            System.out.println(
                    "Error saving messages: " + e.getMessage());
        }
    }

    /*
     * Escapes special characters for valid JSON strings.
     */
    private String escapeJSON(String text) {

        if (text == null) {
            return "";
        }

        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Returns the message ID.
    public String getMessageID() {
        return messageID;
    }

    // Returns the message number.
    public int getMessageNumber() {
        return messageNumber;
    }

    // Returns the recipient number.
    public String getRecipient() {
        return recipient;
    }

    // Returns the message text.
    public String getMessage() {
        return message;
    }

    // Returns the message hash.
    public String getMessageHash() {
        return messageHash;
    }

    // Returns the message status.
    public String getStatus() {
        return status;
    }
}
    