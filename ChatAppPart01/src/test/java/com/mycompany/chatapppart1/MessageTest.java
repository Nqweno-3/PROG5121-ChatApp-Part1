/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;


/**
 *
 * @author Student
 */
public class MessageTest {

    public static void main(String[] args) {

        Message msg = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        System.out.println("Message ID valid: "
                + msg.checkMessageID());

        System.out.println(msg.checkRecipientCell());

        System.out.println(msg.checkMessageLength());

        System.out.println("Message Hash: "
                + msg.createMessageHash());

        System.out.println(msg.SentMessage("Send Message"));

        System.out.println(msg.printMessages());

        System.out.println("Total messages sent: "
                + msg.returnTotalMessagess());
    }
}