/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 */
package com.mycompany.chatapppart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {

    @Test
    public void testMessageID() {
        Message msg = new Message("+27718693002", "Hello", 0);
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testValidRecipientCell() {
        Message msg = new Message("+27718693002", "Hello", 0);

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipientCell() {
        Message msg = new Message("0718693002", "Hello", 0);

        assertTrue(
                msg.checkRecipientCell().contains("incorrectly formatted")
        );
    }

    @Test
    public void testValidMessageLength() {
        Message msg = new Message("+27718693002", "Hello", 0);

        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageExceedsLength() {
        String longMessage = "A".repeat(251);
        Message msg = new Message("+27718693002", longMessage, 0);

        assertTrue(
                msg.checkMessageLength().contains("exceeds 250 characters")
        );
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message(
                "+27718693002",
                "Hi Minqweno, can you join us for dinner tonight?",
                0
        );

        String expectedHash =
                msg.getMessageID().substring(0, 2) + ":0:HITONIGHT";

        assertEquals(expectedHash, msg.createMessageHash());
    }
}