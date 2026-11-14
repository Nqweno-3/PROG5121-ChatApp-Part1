/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 */
package com.mycompany.chatapppart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Tests message validation, message hashes,
 * sending, storing and disregard confirmation.
 *
 * @author Student
 */
public class MessageTest {

    // Test 1: Message ID contains exactly 10 digits.
    @Test
    public void testMessageID() {

        Message msg = new Message(
                "+27718693002", "Hello", 0);

        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
        assertTrue(msg.getMessageID().matches("[0-9]{10}"));
    }

    // Test 2: Valid recipient cellphone number.
    @Test
    public void testValidRecipientCell() {

        Message msg = new Message(
                "+27718693002", "Hello", 0);

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    // Test 3: Recipient without international code.
    @Test
    public void testInvalidRecipientCell() {

        Message msg = new Message(
                "0718693002", "Hello", 0);

        assertTrue(
                msg.checkRecipientCell()
                        .contains("incorrectly formatted")
        );
    }

    // Test 4: Recipient number is too short.
    @Test
    public void testRecipientTooShort() {

        Message msg = new Message(
                "+27718693", "Hello", 0);

        assertTrue(
                msg.checkRecipientCell()
                        .contains("incorrectly formatted")
        );
    }

    // Test 5: Recipient contains letters.
    @Test
    public void testRecipientContainsLetters() {

        Message msg = new Message(
                "+27718ABC002", "Hello", 0);

        assertTrue(
                msg.checkRecipientCell()
                        .contains("incorrectly formatted")
        );
    }

    // Test 6: Valid message length.
    @Test
    public void testValidMessageLength() {

        Message msg = new Message(
                "+27718693002", "Hello", 0);

        assertEquals(
                "Message ready to send.",
                msg.checkMessageLength()
        );
    }

    // Test 7: Message contains exactly 250 characters.
    @Test
    public void testMessageLengthExactly250() {

        Message msg = new Message(
                "+27718693002",
                "A".repeat(250),
                1
        );

        assertEquals(
                "Message ready to send.",
                msg.checkMessageLength()
        );
    }

    // Test 8: Message exceeds 250 characters.
    @Test
    public void testMessageExceedsLength() {

        Message msg = new Message(
                "+27718693002",
                "A".repeat(251),
                2
        );

        assertEquals(
                "Message exceeds 250 characters by 1; "
                + "please reduce the size.",
                msg.checkMessageLength()
        );
    }

    // Test 9: Empty message is rejected.
    @Test
    public void testEmptyMessage() {

        Message msg = new Message(
                "+27718693002", "", 3);

        assertEquals(
                "Please enter a message.",
                msg.checkMessageLength()
        );
    }

    // Test 10: Correct message hash.
    @Test
    public void testMessageHash() {

        Message msg = new Message(
                "+27718693002",
                "Hi Minqweno, can you join us "
                + "for dinner tonight?",
                0
        );

        String expectedHash =
                msg.getMessageID().substring(0, 2)
                + ":0:HITONIGHT";

        assertEquals(
                expectedHash,
                msg.createMessageHash()
        );
    }

    // Test 11: Hash contains correct message number.
    @Test
    public void testMessageHashWithDifferentNumber() {

        Message msg = new Message(
                "+27718693002",
                "Hello World",
                5
        );

        String expectedHash =
                msg.getMessageID().substring(0, 2)
                + ":5:HELLOWORLD";

        assertEquals(
                expectedHash,
                msg.getMessageHash()
        );
    }

    // Test 12: Successfully send a message.
    @Test
    public void testSendMessage() {

        Message msg = new Message(
                "+27718693002",
                "Hello there",
                6
        );

        assertEquals(
                "Message successfully sent.",
                msg.SentMessage("Send")
        );

        assertEquals("SENT", msg.getStatus());

        assertTrue(
                Message.getSentMessages().contains(msg)
        );
    }

    // Test 13: Disregard after confirmation.
    @Test
    public void testDisregardMessage() {

        Message msg = new Message(
                "+27718693002",
                "This message is not needed",
                7
        );

        assertEquals(
                "Press 0 to delete the message.",
                msg.SentMessage("Disregard")
        );

        assertEquals("PENDING", msg.getStatus());

        assertTrue(msg.confirmDisregard("0"));

        assertEquals("DISREGARDED", msg.getStatus());

        assertTrue(
                Message.getDisregardedMessages().contains(msg)
        );
    }

    // Test 14: Invalid confirmation must not
    // disregard a message.
    @Test
    public void testDisregardConfirmation() {

        Message msg = new Message(
                "+27718693002",
                "Cancel this message",
                8
        );

        assertFalse(msg.confirmDisregard("1"));

        assertEquals("PENDING", msg.getStatus());

        assertFalse(
                Message.getDisregardedMessages().contains(msg)
        );

        assertTrue(msg.confirmDisregard("0"));

        assertEquals("DISREGARDED", msg.getStatus());

        // A processed message cannot be disregarded twice.
        assertFalse(msg.confirmDisregard("0"));
    }

    // Test 15: Successfully store a message.
    @Test
    public void testStoreMessage() {

        Message msg = new Message(
                "+27718693002",
                "Store this message",
                9
        );

        assertEquals(
                "Message successfully stored.",
                msg.SentMessage("Store")
        );

        assertEquals("STORED", msg.getStatus());

        assertTrue(
                Message.getStoredMessages().contains(msg)
        );
    }

    // Test 16: Invalid message cannot be sent.
    @Test
    public void testInvalidMessageCannotBeSent() {

        Message msg = new Message(
                "0718693002",
                "Hello",
                10
        );

        assertEquals(
                "Message could not be sent. "
                + "Check the message details.",
                msg.SentMessage("Send")
        );

        assertEquals("PENDING", msg.getStatus());
    }

    // Test 17: Message cannot be sent twice.
    @Test
    public void testMessageCannotBeSentTwice() {

        Message msg = new Message(
                "+27718693002",
                "Send once",
                11
        );

        assertEquals(
                "Message successfully sent.",
                msg.SentMessage("Send")
        );

        assertEquals(
                "Message has already been processed.",
                msg.SentMessage("Send")
        );
    }

    // Test 18: Invalid message action.
    @Test
    public void testInvalidMessageAction() {

        Message msg = new Message(
                "+27718693002",
                "Hello",
                12
        );

        assertEquals(
                "Invalid message option.",
                msg.SentMessage("Invalid")
        );

        assertEquals("PENDING", msg.getStatus());
    }
}
