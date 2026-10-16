/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

/**
 *
 * @author Student
 */
public class Message {
    
// Declare variables to store the message details.
private final String messageID;
private final String recipient;
private String messageText;
private String messageStatus;

// Constructor to initialise the message details.
public Message(String messageID, String recipient,
String messageText, String messageStatus) {
this.messageID = messageID;
this.recipient = recipient;
this.messageText = messageText;
this.messageStatus = messageStatus;
}

// Method to return the unique message ID.
public String getMessageID() {
return messageID;
}

// Method to return the recipient's details.
public String getRecipient() {
return recipient;
}

// Method to return the text of the message.
public String getMessageText() {
return messageText;
}

// Method to update the message text.
public void setMessageText(String messageText) {
this.messageText = messageText;
}

// Method to return the message status.
public String getMessageStatus() {
return messageStatus;
}

// Method to update the message status.
public void setMessageStatus(String messageStatus) {
this.messageStatus = messageStatus;
}

// Method to display all the message details.
public void displayMessage() {
System.out.println("Message ID: " + messageID);
System.out.println("Recipient: " + recipient);
System.out.println("Message: " + messageText);
System.out.println("Message Status: " + messageStatus);
}

// Main method to run and test the Message class.
public static void main(String[] args) {
    
// Create an object of the Message class.
Message message = new Message(
"MSG001",
"+27812345678",
"Hello, how are you?",
"Sent"
);

// Display the message details.
message.displayMessage();
}
}