/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.chatapppart01;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    Login login = new Login();
    
    //---USERNAME TEST---
    /*Check if all the conditions were met when the user was creating 
    their username, if the username met all the conditions then it should
    be accepted.
    */
    @Test
    public void testValidUserName() {
        assertTrue(login.checkUserName ("mimi_1"));
        
    }
    
}
