/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.chatapppart1;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Uses the prescribed message data to test
 * message collections, searches, deletion and reports.
 *
 * @author Student
 */
public class MessageManagerTest {

    private MessageManager manager;

    private Message message1;
    private Message message2;
    private Message message3;
    private Message message4;
    private Message message5;

    @BeforeEach
    public void setUp() {

        manager = new MessageManager();

        message1 = new Message(
                "+27834557896",
                "Did you get the cake?",
                1
        );

        message2 = new Message(
                "+27838884567",
                "Where are you? You are late! "
                + "I have asked you to be on time.",
                2
        );

        message3 = new Message(
                "+27834484567",
                "Yohoooo, I am at your gate.",
                3
        );

        message4 = new Message(
                "0838884567",
                "It is dinner time!",
                4
        );

        message5 = new Message(
                "+27838884567",
                "Ok, I am leaving without you.",
                5
        );
    }

    // Sent messages array contains
    // the expected sent message.
    @Test
    public void testSentMessagesArray() {

        assertEquals(
                "Message successfully sent.",
                message1.SentMessage("Send")
        );

        assertTrue(
                Message.getSentMessages().contains(message1)
        );

        assertEquals(
                "Did you get the cake?",
                message1.getMessage()
        );
    }

    // Disregarded messages array.
    @Test
    public void testDisregardedMessagesArray() {

        assertTrue(message3.confirmDisregard("0"));

        assertTrue(
                Message.getDisregardedMessages()
                        .contains(message3)
        );

        assertEquals(
                "DISREGARDED",
                message3.getStatus()
        );
    }

    // Stored messages array.
    @Test
    public void testStoredMessagesArray() {

        assertEquals(
                "Message successfully stored.",
                message2.SentMessage("Store")
        );

        assertEquals(
                "Message successfully stored.",
                message5.SentMessage("Store")
        );

        List<Message> stored =
                Message.getStoredMessages();

        assertTrue(stored.contains(message2));
        assertTrue(stored.contains(message5));
    }

    // Message hash collection.
    @Test
    public void testMessageHashArray() {

        message1.SentMessage("Send");

        assertTrue(
                Message.getMessageHashes().contains(
                        message1.getMessageHash()
                )
        );
    }

    // Message ID collection.
    @Test
    public void testMessageIDArray() {

        message1.SentMessage("Send");

        assertTrue(
                Message.getMessageIDs().contains(
                        message1.getMessageID()
                )
        );
    }

    // Display the longest stored message.
    @Test
    public void testLongestStoredMessage() {

        message2.SentMessage("Store");
        message5.SentMessage("Store");

        Message longest = manager.findLongestMessage();

        assertNotNull(longest);

        assertEquals(
                "Where are you? You are late! "
                + "I have asked you to be on time.",
                longest.getMessage()
        );
    }

    // Search for a message using its ID.
    @Test
    public void testSearchMessageByID() {

        message1.SentMessage("Send");

        Message result = manager.searchMessage(
                message1.getMessageID()
        );

        assertNotNull(result);

        assertEquals(
                "Did you get the cake?",
                result.getMessage()
        );

        assertEquals(
                "+27834557896",
                result.getRecipient()
        );
    }

    // Search for a message ID that does not exist.
    @Test
    public void testSearchMessageByIDNotFound() {

        assertNull(
                manager.searchMessage("0000000000")
        );
    }

    // Search for all stored messages
    // belonging to a particular recipient.
    @Test
    public void testSearchByRecipient() {

        message2.SentMessage("Store");
        message5.SentMessage("Store");

        List<Message> results =
                manager.searchByRecipient(
                        "+27838884567"
                );

        assertTrue(results.contains(message2));
        assertTrue(results.contains(message5));

        assertEquals(
                "Where are you? You are late! "
                + "I have asked you to be on time.",
                results.stream()
                        .filter(m -> m == message2)
                        .findFirst()
                        .orElseThrow()
                        .getMessage()
        );

        assertEquals(
                "Ok, I am leaving without you.",
                results.stream()
                        .filter(m -> m == message5)
                        .findFirst()
                        .orElseThrow()
                        .getMessage()
        );
    }

    // Delete a message using its hash.
    @Test
    public void testDeleteMessageByHash() {

        message2.SentMessage("Store");

        String hash = message2.getMessageHash();

        assertTrue(
                manager.deleteMessage(hash)
        );

        assertFalse(
                Message.getStoredMessages().contains(message2)
        );

        assertNull(
                manager.searchMessage(
                        message2.getMessageID()
                )
        );
    }

    // Deleted message must not appear
    // in the stored message report collection.
    @Test
    public void testDeletedMessageNotInStoredMessages() {

        message2.SentMessage("Store");

        assertTrue(
                manager.deleteMessage(
                        message2.getMessageHash()
                )
        );

        assertFalse(
                Message.getStoredMessages()
                        .stream()
                        .anyMatch(m ->
                                m.getMessageHash().equals(
                                        message2.getMessageHash()
                                ))
        );
    }

    // Message report must contain
    // stored message details.
    @Test
    public void testStoredMessageReportData() {

        message2.SentMessage("Store");
        message5.SentMessage("Store");

        List<Message> stored =
                Message.getStoredMessages();

        assertTrue(
                stored.stream().anyMatch(m ->
                        m.getMessageHash().equals(
                                message2.getMessageHash()
                        )
                        && m.getRecipient().equals(
                                "+27838884567"
                        )
                        && m.getMessage().equals(
                                "Where are you? You are late! "
                                + "I have asked you to be on time."
                        ))
        );

        assertTrue(
                stored.stream().anyMatch(m ->
                        m.getMessageHash().equals(
                                message5.getMessageHash()
                        )
                        && m.getMessage().equals(
                                "Ok, I am leaving without you."
                        ))
        );
    }

    // Check that message 4 preserves
    // the developer-provided test data.
    @Test
    public void testDeveloperMessageFour() {

        assertEquals(
                "0838884567",
                message4.getRecipient()
        );

        assertEquals(
                "It is dinner time!",
                message4.getMessage()
        );
    }
}
