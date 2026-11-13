/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.chatapppart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest {

    // Creates and sends a test message.
    private Message createSentMessage(String recipient, String text) {

        Message message = new Message(recipient, text, 1);

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("Send")
        );

        return message;
    }

    @Test
    public void testSearchMessage() {

        MessageManager manager = new MessageManager();

        Message message = createSentMessage(
                "+27821234567", "Hello QuickChat"
        );

        Message result = manager.searchMessage(
                message.getMessageID()
        );

        assertNotNull(result);
        assertEquals(message.getMessageID(), result.getMessageID());
    }

    @Test
    public void testSearchByRecipient() {

        MessageManager manager = new MessageManager();

        Message message = createSentMessage(
                "+27821234568", "Testing recipient search"
        );

        assertTrue(
                manager.searchByRecipient("+27821234568")
                        .contains(message)
        );
    }

    @Test
    public void testDeleteMessage() {

        MessageManager manager = new MessageManager();

        Message message = createSentMessage(
                "+27821234569", "Testing message deletion"
        );

        assertTrue(
                manager.deleteMessage(message.getMessageHash())
        );

        assertNull(
                manager.searchMessage(message.getMessageID())
        );
    }

    @Test
    public void testGetMessageCount() {

        MessageManager manager = new MessageManager();

        int previousCount = manager.getMessageCount();

        createSentMessage(
                "+27821234570", "Testing message count"
        );

        assertEquals(previousCount + 1, manager.getMessageCount());
    }

    @Test
    public void testDisplayMessages() {

        MessageManager manager = new MessageManager();

        createSentMessage(
                "+27821234571", "Testing display messages"
        );

        assertDoesNotThrow(manager::displayMessages);
    }

    @Test
    public void testDisplayReport() {

        MessageManager manager = new MessageManager();

        createSentMessage(
                "+27821234572", "Testing message report"
        );

        assertDoesNotThrow(manager::displayReport);
    }
}
