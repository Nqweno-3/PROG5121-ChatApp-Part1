/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Handles message validation, sending,
 * disregarding, JSON storage and message lists.
 *
 * @author Student
 */
public final class Message {

    private static final String JSON_FILE = "storedMessages.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    @SerializedName(value = "messageID", alternate = {"messageId"})
    private final String messageID;

    private final int messageNumber;
    private final String recipient;
    private final String message;
    private final String messageHash;

    private String status;

    private static int totalMessagesSent = 0;

    // Part 3: Message collections
    private static final List<Message> sentMessages =
            new ArrayList<>();

    private static final List<Message> disregardedMessages =
            new ArrayList<>();

    private static final List<Message> storedMessages =
            new ArrayList<>();

    private static final List<String> messageHashes =
            new ArrayList<>();

    private static final List<String> messageIDs =
            new ArrayList<>();

    // Constructor
    public Message(String recipient, String message,
                   int messageNumber) {

        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
        this.status = "PENDING";
    }

    // Generate a random ten-digit message ID.
    private String generateMessageID() {

        long number = ThreadLocalRandom.current().nextLong(
                1_000_000_000L,
                10_000_000_000L
        );

        return String.valueOf(number);
    }

    // Validate message ID.
    public boolean checkMessageID() {

        return messageID != null
                && messageID.matches("[0-9]{10}");
    }

    // Validate South African international cellphone number.
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("\\+27[6-8][0-9]{8}")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted "
                + "or does not contain an international code. "
                + "Please correct the number and try again.";
    }

    // Validate message length.
    public String checkMessageLength() {

        if (message == null || message.trim().isEmpty()) {
            return "Please enter a message.";
        }

        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        return "Message exceeds 250 characters by "
                + (message.length() - 250)
                + "; please reduce the size.";
    }

    // Generate the message hash.
    public String createMessageHash() {

        if (messageID == null
                || message == null
                || message.trim().isEmpty()) {

            return "";
        }

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0].replaceAll(
                "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$",
                ""
        );

        String lastWord = words[words.length - 1].replaceAll(
                "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$",
                ""
        );

        return (
                messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord
        ).toUpperCase();
    }

    // Process a message action.
    public String SentMessage(String choice) {

        if (choice == null) {
            return "Invalid message option.";
        }

        if (!"PENDING".equals(status)) {
            return "Message has already been processed.";
        }

        // Send message
        if (choice.equalsIgnoreCase("Send Message")
                || choice.equalsIgnoreCase("Send")) {

            if (!isValidMessage()) {
                return "Message could not be sent. "
                        + "Check the message details.";
            }

            status = "SENT";
            sentMessages.add(this);
            recordMessageDetails();
            totalMessagesSent++;

            return "Message successfully sent.";
        }

        // Disregard requires confirmation.
        if (choice.equalsIgnoreCase("Disregard Message")
                || choice.equalsIgnoreCase("Disregard")
                || choice.equalsIgnoreCase("Discard")) {

            return "Press 0 to delete the message.";
        }

        // Store message
        if (choice.equalsIgnoreCase("Store Message")
                || choice.equalsIgnoreCase("Store")) {

            if (!isValidMessage()) {
                return "Message could not be stored. "
                        + "Check the message details.";
            }

            status = "STORED";
            storedMessages.add(this);

            if (storeMessage()) {
                recordMessageDetails();
                return "Message successfully stored.";
            }

            storedMessages.remove(this);
            status = "PENDING";

            return "Message could not be stored. "
                    + "Please try again.";
        }

        return "Invalid message option.";
    }

    // Confirm disregard only when user enters 0.
    public boolean confirmDisregard(String confirmation) {

        if (!"PENDING".equals(status)
                || !"0".equals(confirmation)) {

            return false;
        }

        status = "DISREGARDED";
        disregardedMessages.add(this);
        recordMessageDetails();

        return true;
    }

    // Check message details before sending or storing.
    private boolean isValidMessage() {

        return checkMessageID()
                && checkRecipientCell().equals(
                        "Cell phone number successfully captured.")
                && checkMessageLength().equals(
                        "Message ready to send.");
    }

    // Record message IDs and hashes.
    private void recordMessageDetails() {

        if (!messageIDs.contains(messageID)) {
            messageIDs.add(messageID);
        }

        if (!messageHashes.contains(messageHash)) {
            messageHashes.add(messageHash);
        }
    }

    // Display sent messages as text.
    public String printMessages() {

        StringBuilder output = new StringBuilder();

        for (Message sent : sentMessages) {

            output.append("Message ID: ")
                    .append(sent.messageID)
                    .append("\n");

            output.append("Message Hash: ")
                    .append(sent.messageHash)
                    .append("\n");

            output.append("Recipient: ")
                    .append(sent.recipient)
                    .append("\n");

            output.append("Message: ")
                    .append(sent.message)
                    .append("\n\n");
        }

        return output.toString();
    }

    // Method name.
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    // Save stored messages to JSON using Gson.
    public boolean storeMessage() {

        try (FileWriter writer = new FileWriter(JSON_FILE)) {

            gson.toJson(storedMessages, writer);
            return true;

        } catch (IOException e) {

            System.out.println(
                    "Error saving messages: " + e.getMessage());

            return false;
        }
    }

    // Load previously stored messages from JSON.
    public static boolean loadStoredMessages() {

        File file = new File(JSON_FILE);

        if (!file.exists()) {
            return true;
        }

        try (Reader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<List<Message>>() {}.getType();

            List<Message> loaded =
                    gson.fromJson(reader, listType);

            storedMessages.clear();

            if (loaded != null) {

                for (Message loadedMessage : loaded) {

                    if (loadedMessage != null
                            && loadedMessage.messageID != null
                            && loadedMessage.messageHash != null) {

                        loadedMessage.status = "STORED";
                        storedMessages.add(loadedMessage);
                        loadedMessage.recordMessageDetails();
                    }
                }
            }

            return true;

        } catch (IOException | RuntimeException e) {

            System.out.println(
                    "Error loading stored messages: "
                    + e.getMessage());

            return false;
        }
    }

    // Part 3: Return message collections.
    public static List<Message> getSentMessages() {
        return new ArrayList<>(sentMessages);
    }

    public static List<Message> getDisregardedMessages() {
        return new ArrayList<>(disregardedMessages);
    }

    public static List<Message> getStoredMessages() {
        return new ArrayList<>(storedMessages);
    }

    public static List<String> getMessageHashes() {
        return new ArrayList<>(messageHashes);
    }

    public static List<String> getMessageIDs() {
        return new ArrayList<>(messageIDs);
    }

    // Delete a sent message by hash.
    public static boolean removeSentMessage(String hash) {

        if (hash == null) {
            return false;
        }

        for (int i = 0; i < sentMessages.size(); i++) {

            Message current = sentMessages.get(i);

            if (current.messageHash.equals(hash)) {

                sentMessages.remove(i);
                messageIDs.remove(current.messageID);
                messageHashes.remove(current.messageHash);

                return true;
            }
        }

        return false;
    }

    // Delete a stored message by hash.
    public static boolean removeStoredMessage(String hash) {

        if (hash == null) {
            return false;
        }

        for (int i = 0; i < storedMessages.size(); i++) {

            Message current = storedMessages.get(i);

            if (current.messageHash.equals(hash)) {

                storedMessages.remove(i);

                if (current.storeMessage()) {

                    messageIDs.remove(current.messageID);
                    messageHashes.remove(current.messageHash);

                    return true;
                }

                storedMessages.add(i, current);
                return false;
            }
        }

        return false;
    }

    // Getter methods
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getStatus() {
        return status;
    }
}
