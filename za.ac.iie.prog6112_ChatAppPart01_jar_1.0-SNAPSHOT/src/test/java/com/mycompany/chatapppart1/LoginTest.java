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
 * The LoginTest class is used to test the different methods
 * found in the Login class.
 *
 * Each test compares the result of a method with the
 * expected outcome.
 *
 * @author Student
 */
public class LoginTest {

    /**
     * Tests the checkUserName() method from the Login class.
     *
     * A valid username is used that includes an underscore
     * and is five characters or less.
     */
    @Test
    public void testValidUserName() {

        //Create a Login object using valid user information.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        //Confirm that the username passes the validation check.
        assertTrue(login.checkUserName());
    }

    /**
     * Tests the checkPasswordComplexity() method from the Login class.
     *
     * The password used in this test contains an uppercase letter,
     * a number and a special character.
     */
    @Test
    public void testValidPasswordComplexity() {

        //Set up a Login object with a password that meets the requirements.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        //Verify that the password is accepted as valid.
        assertTrue(login.checkPasswordComplexity());
    }

    /**
     * Tests the checkCellPhoneNumber() method from the Login class.
     *
     * The cellphone number used begins with +27 and has
     * the correct number of digits.
     */
    @Test
    public void testValidCellPhoneNumber() {

        //Set up a Login object with a correctly formatted cellphone number.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        //Make sure that the cellphone number passes the validation.
        assertTrue(login.checkCellPhoneNumber());
    }

    /**
     * Tests the registerUser() method from the Login class.
     *
     * This test checks that the correct messages are returned
     * when all the registration details are valid.
     */
   
@Test
public void testRegisterUser() {

    //Create a Login object with valid registration details.
    Login login = new Login(
            "Kyle",
            "Ngati",
            "kyl_1",
            "Ch&&sec@ke99!",
            "+27838968976");

    //Save the registration message returned by the method.
    String result = login.registerUser();

    //Compare the expected message with the result from registerUser().
    assertEquals(
            "Username successfully captured.\n"
            + "Password successfully captured.\n"
            + "Cell phone number successfully added.",
            result);
}



    /**
     * Tests the loginUser() method from the Login class.
     *
     * The correct username and password are entered to check
     * whether the login is accepted.
     */
    @Test
    public void testLoginUser() {

        //Create a Login object containing the registered credentials.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        //Provide the username being used for the login attempt.
        login.setEnteredUsername("kyl_1");

        //Provide the password being used for the login attempt.
        login.setEnteredPassword("Ch&&sec@ke99!");

        //Confirm that the login details are accepted.
        assertTrue(login.loginUser());
    }

    /**
     * Tests the returnLoginStatus() method from the Login class.
     *
     * This test checks the message displayed when valid
     * login details are entered.
     */
    @Test
    public void testReturnLoginStatus() {

        //Create a Login object with valid registration information.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        //Enter the registered username.
        login.setEnteredUsername("kyl_1");

        //Enter the registered password.
        login.setEnteredPassword("Ch&&sec@ke99!");

        /*
         * Check that the correct welcome message is displayed
         * after a successful login.
         */
        assertEquals(
                "Welcome Kyle Ngati, it is great to see you again.",
                login.returnLoginStatus());
    }

    /**
     * Tests the checkUserName() method using an invalid username.
     *
     * The username in this test does not contain an underscore
     * and is also longer than the allowed length.
     */
    @Test
    public void testInvalidUserName() {

        //Create a Login object with an incorrectly formatted username.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        //Confirm that the username fails the validation check.
        assertFalse(login.checkUserName());
    }

    /**
     * Tests the checkPasswordComplexity() method using an invalid password.
     *
     * The password does not meet the required complexity rules
     * because it does not include the required uppercase letter,
     * number and special character.
     */
    @Test
    public void testInvalidPasswordComplexity() {

        //Create a Login object with an invalid password.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "password",
                "+27838968976"
        );

        //Check that the password is rejected by the validation method.
        assertFalse(login.checkPasswordComplexity());
    }

    /**
     * Tests the checkCellPhoneNumber() method with an invalid number.
     *
     * The cellphone number does not use +27, which is required
     * for the South African international format.
     */
    @Test
    public void testInvalidPhoneNumber() {

        //Create a Login object with an incorrectly formatted phone number.
        Login login = new Login(
                "Kyle",
                "Ngati",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        //Verify that the invalid number is rejected.
        assertFalse(login.checkCellPhoneNumber());
    }
}
