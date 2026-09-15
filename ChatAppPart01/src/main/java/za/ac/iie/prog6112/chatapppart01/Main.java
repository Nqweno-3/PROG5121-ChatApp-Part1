/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.chatapppart01;

import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        
        //Scanner allows the user to enter information
        Scanner input = new Scanner(System.in);
        
        //Create an object of the Login class so we can call its methods
        Login login = new Login();
        
        //--- REGISTRATION SECTION ---
        System.out.println("=== USER REGISTRATION ===");
        while (true) {
            
        System.out.print("Enter a username: ");
        String username = input.nextLine();
        
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        
        //Prompting the user to insert number
        
        System.out.print("Enter your South African phone number (+27...): ");
        String phone = input.nextLine();
        
        // Call the registerUser method and store the message it returns
        String response = login.registerUser(username, password, phone);
        
        //Show the registration message
        System.out.println(response);
        
        if (response.equals("User registered successfully")) {
            break;
        }
        System.out.println("Please try again");
        }
        
        //---LOGIN SECTION ---
        System.out.println("\n=== USER LOGIN");
        
        System.out.println("Enter your username: ");
        String loginUsername = input.nextLine();
        
        System.out.println("Enter your password: ");
        String loginPassword = input.nextLine();
        
        //Call loginUser to check if details match the stored ones
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        
        //Print out the correct login message
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
    }
}
    

