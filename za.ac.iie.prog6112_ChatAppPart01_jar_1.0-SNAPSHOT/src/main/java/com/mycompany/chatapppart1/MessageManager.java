/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapppart1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Handles message searches, stored message
 * displays, deletion and reports.
 *
 * @author Student
 */
public class MessageManager {

    // Combine sent and stored messages for searches.
    private List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();

        messages.addAll(Message.getSentMessages());
        messages.addAll(Message.getStoredMessages());

        return messages;
    }

    // Display successfully sent messages.
    public void displayMessages() {

        List<Message> messages = Message.getSentMessages();

        if (messages.isEmpty()) {
            System.out.println("No sent messages available.");
            return;
        }

        System.out.println("\n========== SENT MESSAGES ==========");

        for (Message message : messages) {

            printMessageDetails(message);
        }
    }

    // Display messages loaded from JSON.
    public void displayStoredMessages() {

        List<Message> messages = Message.getStoredMessages();

        if (messages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        System.out.println("\n========== STORED MESSAGES ==========");

        for (Message message : messages) {

            printMessageDetails(message);
        }
    }

    // Display sender and recipient
    // of all stored messages.
    public void displaySenderAndRecipient() {

        displaySenderAndRecipient("QuickChat User");
    }

    // Allows Main.java to supply the registered sender.
    public void displaySenderAndRecipient(String sender) {

        List<Message> messages = Message.getStoredMessages();

        if (messages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        System.out.println(
                "\n========== STORED MESSAGE RECIPIENTS =========="
        );

        for (Message message : messages) {

            System.out.println("Sender: " + sender);

            System.out.println(
                    "Recipient: " + message.getRecipient()
            );

            System.out.println("------------------------------");
        }
    }

    // Find the longest stored message.
    public Message findLongestMessage() {

        List<Message> messages = Message.getStoredMessages();

        if (messages.isEmpty()) {
            return null;
        }

        Message longest = messages.get(0);

        for (Message message : messages) {

            if (message.getMessage() != null
                    && (longest.getMessage() == null
                    || message.getMessage().length()
                    > longest.getMessage().length())) {

                longest = message;
            }
        }

        return longest;
    }

    // Display the longest stored message.
    public void displayLongestMessage() {

        Message longest = findLongestMessage();

        if (longest == null) {
            System.out.println("No stored messages available.");
            return;
        }

        System.out.println(
                "\n========== LONGEST STORED MESSAGE =========="
        );

        System.out.println(
                "Recipient: " + longest.getRecipient()
        );

        System.out.println(
                "Message: " + longest.getMessage()
        );

        System.out.println(
                "Message Length: "
                + longest.getMessage().length()
                + " characters"
        );
    }

    // Search for a message using its ID.
    public Message searchMessage(String messageID) {

        if (messageID == null) {
            return null;
        }

        for (Message message : getAllMessages()) {

            if (messageID.equals(message.getMessageID())) {
                return message;
            }
        }

        return null;
    }

    // Search all sent and stored
    // messages for a particular recipient.
    public List<Message> searchByRecipient(String recipient) {

        List<Message> results = new ArrayList<>();

        if (recipient == null) {
            return results;
        }

        for (Message message : getAllMessages()) {

            if (recipient.equals(message.getRecipient())) {
                results.add(message);
            }
        }

        return results;
    }

    // Delete a message using its hash.
    public boolean deleteMessage(String messageHash) {

        if (messageHash == null
                || messageHash.trim().isEmpty()) {

            return false;
        }

        // Check stored messages first.
        if (Message.removeStoredMessage(messageHash)) {
            return true;
        }

        // Also support deletion from sent messages.
        return Message.removeSentMessage(messageHash);
    }

    // Report of all stored messages.
    public void displayReport() {

        List<Message> messages = Message.getStoredMessages();

        if (messages.isEmpty()) {
            System.out.println(
                    "No stored messages available for the report."
            );
            return;
        }

        System.out.println(
                "\n========== QUICKCHAT STORED MESSAGE REPORT =========="
        );

        for (Message message : messages) {

            System.out.println(
                    "Message Hash: " + message.getMessageHash()
            );

            System.out.println(
                    "Recipient: " + message.getRecipient()
            );

            System.out.println(
                    "Message: " + message.getMessage()
            );

            System.out.println("------------------------------");
        }

        System.out.println(
                "Total stored messages: " + messages.size()
        );
    }

    // Count successfully sent messages.
    public int getMessageCount() {

        return Message.getSentMessages().size();
    }

    // Count stored messages.
    public int getStoredMessageCount() {

        return Message.getStoredMessages().size();
    }

    // Display complete message details.
    private void printMessageDetails(Message message) {

        System.out.println(
                "Message ID: " + message.getMessageID()
        );

        System.out.println(
                "Message Hash: " + message.getMessageHash()
        );

        System.out.println(
                "Recipient: " + message.getRecipient()
        );

        System.out.println(
                "Message: " + message.getMessage()
        );

        System.out.println(
                "Status: " + message.getStatus()
        );

        System.out.println("------------------------------");
    }
}
