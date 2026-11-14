/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java
 * to edit this template
 */
package com.mycompany.chatapppart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Tests registration, username validation,
 * password complexity, cellphone validation
 * and successful and unsuccessful login.
 *
 * @author Student
 */
public class LoginTest {

    // Test 1: Valid username
    @Test
    public void testValidUserName() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkUserName());
    }

    // Test 2: Invalid username
    @Test
    public void testInvalidUserName() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(login.checkUserName());
    }

    // Test 3: Username exceeds five characters
    @Test
    public void testUsernameExceedsFiveCharacters() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyle_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(login.checkUserName());
    }

    // Test 4: Valid password complexity
    @Test
    public void testValidPasswordComplexity() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkPasswordComplexity());
    }

    // Test 5: Invalid password complexity
    @Test
    public void testInvalidPasswordComplexity() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "password",
                "+27838968976"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    // Test 6: Valid cellphone number
    @Test
    public void testValidCellPhoneNumber() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkCellPhoneNumber());
    }

    // Test 7: Invalid cellphone number
    @Test
    public void testInvalidPhoneNumber() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        assertFalse(login.checkCellPhoneNumber());
    }

    // Test 8: Successful registration
    @Test
    public void testRegisterUser() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        String result = login.registerUser();

        assertEquals(
                "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.",
                result
        );
    }

    // Test 9: Successful login
    @Test
    public void testLoginUser() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        login.setEnteredUsername("kyl_1");
        login.setEnteredPassword("Ch&&sec@ke99!");

        assertTrue(login.loginUser());
    }

    // Test 10: Unsuccessful login
    @Test
    public void testInvalidLogin() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        login.setEnteredUsername("kyl_1");
        login.setEnteredPassword("WrongPassword123!");

        assertFalse(login.loginUser());
    }

    // Test 11: Successful login status message
    @Test
    public void testReturnLoginStatus() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        login.setEnteredUsername("kyl_1");
        login.setEnteredPassword("Ch&&sec@ke99!");

        assertEquals(
                "Welcome Kyle Ngati, it is great to see you again.",
                login.returnLoginStatus()
        );
    }

    // Test 12: Unsuccessful login status message
    @Test
    public void testInvalidLoginStatus() {

        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        login.setEnteredUsername("wrong");
        login.setEnteredPassword("Ch&&sec@ke99!");

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus()
        );
    }
}
