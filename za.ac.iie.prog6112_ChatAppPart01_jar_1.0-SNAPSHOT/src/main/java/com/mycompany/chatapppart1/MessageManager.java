/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Student
 */
public class MessageManager {
    
    // Displays all successfully sent messages.
    public void displayMessages() {

        List<Message> messages = Message.getSentMessages();

        if (messages.isEmpty()) {
            System.out.println("No sent messages available.");
            return;
        }

        for (Message message : messages) {

            System.out.println("Message ID: "
                    + message.getMessageID());

            System.out.println("Message Hash: "
                    + message.getMessageHash());

            System.out.println("Recipient: "
                    + message.getRecipient());

            System.out.println("Message: "
                    + message.getMessage());

            System.out.println("----------------------");
        }
    }

    // Searches for a message using its ID.
    public Message searchMessage(String messageID) {

        for (Message message : Message.getSentMessages()) {

            if (message.getMessageID().equals(messageID)) {
                return message;
            }
        }

        return null;
    }

    // Searches for messages using the recipient's number.
    public List<Message> searchByRecipient(String recipient) {

        List<Message> results = new ArrayList<>();

        for (Message message : Message.getSentMessages()) {

            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        return results;
    }

    // Deletes a message using its hash.
    public boolean deleteMessage(String messageHash) {

        return Message.removeSentMessage(messageHash);
    }

    // Displays a report of all sent messages.
    public void displayReport() {

        List<Message> messages = Message.getSentMessages();

        if (messages.isEmpty()) {
            System.out.println("No messages to report.");
            return;
        }

        System.out.println("QUICKCHAT MESSAGE REPORT");

        for (Message message : messages) {

            System.out.println("Message Hash: "
                    + message.getMessageHash());

            System.out.println("Recipient: "
                    + message.getRecipient());

            System.out.println("Message: "
                    + message.getMessage());

            System.out.println("----------------------");
        }
    }

    // Returns the number of currently listed sent messages.
    public int getMessageCount() {

        return Message.getSentMessages().size();
    }
}

    
