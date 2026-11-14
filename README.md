Student Name: Ngati Minqweno
Student Number: ST10535825
Module Name: Programming 1A
Module Code: PROG5121
Assignment: ChatApp Part 1 project

PROJECT OVERVIEW
Quick Chat Part 1 is a Java-based application that demonstrates a basic user registration and login system. The project allows a user to enter their personal details, create a username and password, and provide a 	South African cellphone number. The application checks whether the information entered follows the required rules before allowing the user to log in. The project also includes Junit tests to check that the registration and login methods work correctly.

FEATURES:
•	User registration
•	Username validation
•	Password complexity validation
•	South African cellphone number validation
•	User login
•	Login status messages
•	Junit unit tests for valid and invalid inputs
TECHNOLOGIES USED:
•	Java
•	Maven
•	NetBeans
•	JUnit 5 
•	Java Scanner for user inputs
PROJECT STRUCTURE 
The project is organised into the following main files:
•	Main.java – Contains the main program and handles user input, registration and login.
•	Login.java – Contains the methods used to validate user details and manage the login process.
•	LoginTest.java – Contains JUnit tests for checking the Login class methods.
•	Pom.xml – Contains the Maven project configuration and JUnit dependencies.
•	ReadME.md – Provides information about the project and explains how to use it

HOW THE APPLICATION WORKS

1.	Registration
The user is asked to enter:
•	First Name
•	Last Name
•	Username
•	Password
•	Cellphone number
The application checks that the required information has been entered and that the username, password and cellphone number meet the required conditions.

2.	Username Validation
The username must:
•	Contain an underscore (_)
•	Be no longer than 5 characters, e.g. kyl_1

3.	Password Validation
The password must:
•	Contain at least 8 characters
•	Have at least one uppercase letter
•	Have at least one number
•	Have at least one special character, e.g. Ch&&sec@ke99!

4.	Cellphone Number Validation
The cellphone number must:
•	Starts with +27
•	Contain the correct number of digits
•	Follow the required South African international format, e.g. +27838968976


5.	Login
After successful registration, the user enters their username and password. The application compares the entered details with the registered details. If they match, the user receives a successful login message. If they do not match, an error message is displayed. 

JUnit Testing
The project contains a LoginTest.java file that tests the different methods in the Login class.
The tests check:
•	Valid username
•	Valid password
•	Valid cellphone number 
•	Successful registration
•	Successful login
•	Login status
•	Invalid username
•	Invalid password
•	Invalid cellphone number
There are 9 JUnit tests in total.

RUNNING THE PROJECT
Running the application
Open the project in NetBeans and run the Main.java file. The application will open in the console and ask the user to enter their registration and login details.
Running the tests
The tests can be run using Maven in NetBeans. The project should show:
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
 (when all tests pass successfully).

REQUIREMENTS 
To run this project, you need:
•	Java Development Kit (JDK)
•	Apache Maven
•	NetBeans IDE
•	JUnit 5

REGEX REFERENCE 

Cellphone Number:
The cellphone number validation uses a regular expression to check
that the number follows the South African international format beginning
with +27.


Regex:

`^\+27[0-9]{9}$`

Example:

`+27838968976`

REFERENCES

Oracle. n.d. Java Documentation. Available at:
https://docs.oracle.com/en/java/

Apache Maven. n.d. Maven Documentation. Available at:
https://maven.apache.org/guides/

ICASA. n.d. Numbering Plan Regulations. Available at:
https://www.icasa.org.za/

JUnit. n.d. JUnit 5 User Guide. Available at:
https://junit.org/junit5/docs/current/user-guide/

YOUTUBE Video 
https://youtube.com/shorts/otCD6NTPzio?si=s_WrawHZwDAlZ4Be


PART 2


Quick Chat is a Java console-based messaging application developed for part 2. The application extends the registration and login functionality from part 1 and allows users to send, disregard, and store messages.
FEATURES
•	User registration and login.
•	Menu-driven console interface.
•	Set a limit for the number of messages.
•	Generate a unique 10-digit message id.
•	Validate message IDs and recipient cellphone numbers.
•	Limit messages to 250 characters.
•	Generate a message hash.
•	Send, disregard, or store messages.
•	Display the total number of messages sent.
•	Store messages in JSon format.
•	Automated testing using Junit.

TECHNOLOGIES USED
•	Java
•	Apache Maven Junit
•	Git and GitHub
•	GitHub Desktop

REQUIREMENTS
To run the application, you need:
•	Java Development Kit (JDK)
•	Apache Maven, if it is not included through your IDE that supports Maven projects.

How To Run the Application
1.	Clone or download the repository from GitHub.
2.	Open the project on NetBeans.
3.	Allow Maven to load the project dependencies.
4.	Run the Main.java file.
5.	Register or log in when prompted.
6.	Follow the on-screen menu instructions to use ChatApp.

Main Menu
The application provides these options:
1.	Send Messages
2.	Show recently sent messages
3.	Quit.
The recently sent messages option displays a “Coming Soon.” Message as specified in project requirements.

Message Storage
Messages selected for storage are saved in a JSon file named storedMessages.json. This file stores the message information in Json format.

Testing
The application includes Junit tests for checking the message functionality and related requirements.
To run the tests in NetBeans:
1.	Open the project.
2.	Locate the test classes under Test Packages.
3.	Right-cllick a test class.
4.	Select Test File or run all project tests.

Version Control
GitHub and GitHub Desktop are used to manage the project’s source code and track development progress.

YouTube link: https://youtu.be/cIv5deuFNdg?si=p3eSgmTlyDH-MJBA

# QuickChat — PROG5121 Portfolio of Evidence

## Part 3: Store Data and Display Message Reports

### Project Overview

QuickChat is a Java messaging application developed using Apache NetBeans and Maven as part of the PROG5121 Portfolio of Evidence (POE).

Part 3 extends the functionality developed in Parts 1 and 2 by introducing message collections, JSON storage, message searching, deletion, and reporting.

### Technologies Used

- Java
- Apache NetBeans
- Apache Maven
- Gson (JSON processing)
- JUnit 5 (automated testing)
- Git and GitHub
- GitHub Actions

### Part 3 Features

**1. Message Collections**

The application maintains five collections:

- Sent Messages
- Disregarded Messages
- Stored Messages
- Message Hashes
- Message IDs

These collections are populated as messages are processed.

**2. JSON Message Storage**

Stored messages are saved in `storedMessages.json` using the Gson library.

When the application restarts, previously stored messages are loaded from the JSON file.

**3. Stored Messages Menu**

The application provides the following functions:

- Display all stored messages.
- Display sender and recipient details.
- Identify and display the longest stored message.
- Search for messages using a Message ID.
- Search for messages associated with a particular recipient.
- Delete messages using their Message Hash.
- Generate a report containing stored-message details.

**4. Message Reports**

The application generates a report displaying:

- Message Hash
- Recipient cellphone number
- Message text
- Total number of stored messages

**5. Automated Unit Testing**

JUnit 5 is used to test message collections, searching, deletion, and stored-message functionality.

The project also uses GitHub Actions to run Maven tests automatically when changes are pushed to GitHub.

### How to Run the Application

1. Open the Maven project in Apache NetBeans.
2. Ensure the required Maven dependencies are available.
3. Run `Main.java`.
4. Register and log in using valid credentials.
5. Enter the number of messages to process.
6. Use the main menu to send, disregard, or store messages.
7. Select **Stored Messages** to access the Part 3 functions.

### Testing

To run the automated tests:

1. Open the project in Apache NetBeans.
2. Right-click the project.
3. Select **Test**.
4. Confirm that the tests finish with zero failures and errors.

Alternatively, run:

```bash
mvn test
```

### Project Structure

The main Java classes include:

- `Login.java` — User registration and authentication.
- `Main.java` — Application execution and menus.
- `Message.java` — Message creation, validation, collections, and JSON storage.
- `MessageManager.java` — Message searches, deletion, and reports.

JUnit test classes include:

- `LoginTest.java`
- `MessageTest.java`
- `MessageManagerTest.java`

### Conclusion

Part 3 extends QuickChat into a more complete messaging application by introducing persistent message storage and message management functionality.

The application demonstrates Java programming concepts such as classes, objects, collections, loops, conditional statements, file handling, and automated unit testing.

