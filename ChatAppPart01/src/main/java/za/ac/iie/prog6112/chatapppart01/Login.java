/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.chatapppart01;

/**
 *
 * @author Student
 */
public class Login {
    
    //Declare variables that are going to be used by the user
    
    String username;
    String password;
    String phoneNumber;
    
    //Username validation
    public boolean checkUserName(String username){
        return username.contains("_")&& username.length() <= 5;
    }
    
    //Password validation
    public boolean checkpasswordComplextiy(String password) {
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            
            if (Character.isUpperCase(c)){
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
        
    }
    
    //Cellphone validation should contain not more than 12 characters long
    public boolean checkCellphoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }
    
    //Register user method
    public String registerUser(String username, String password, String phoneNumber){
        
        if(!checkUserName(username)){
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
            }
        
        
        if (!checkpasswordComplextiy(password)){
            return "password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellphoneNumber(phoneNumber)){
            return "Cell phone number incorrectly formatted or does not contain international code. ";
        }
        
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        
        return"User registered successfully.";
    }
    
    //Login feature
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    //returnLoginStatus()
    public String returnLoginStatus(boolean success){
        if (success){
            return "Welcome" + username + "it is great to see you again.";
        }else{
            return "Username or password incorrect, please try again.";
        }
    }
            
            
}
