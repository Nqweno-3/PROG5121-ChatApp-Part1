QuickChat — PROG5121
**Student Name:** Ngati Minqweno  
---

# Part 1: User Registration and Login

## Project Overview

QuickChat Part 1 is a Java-based application that demonstrates a basic user registration and login system.

The application allows users to enter their personal details, create a username and password, and provide a South African cellphone number. It validates the information entered before allowing users to log in.

The project also includes JUnit tests to verify that the registration and login methods work correctly.

## Features

- User registration
- Username validation
- Password complexity validation
- South African cellphone number validation
- User login
- Login status messages
- JUnit unit tests for valid and invalid inputs

## Technologies Used

- Java
- Apache Maven
- Apache NetBeans
- JUnit 5
- Java Scanner for user input

## Project Structure

The main files used in Part 1 are:

- `Main.java` — Handles user input, registration, and login.
- `Login.java` — Validates user details and manages the login process.
- `LoginTest.java` — Contains JUnit tests for the Login class.
- `pom.xml` — Contains Maven configuration and project dependencies.
- `README.md` — Provides project documentation and instructions.

## How the Application Works

### 1. User Registration

The user enters their first name, last name, username, password, and cellphone number.

The application checks whether the required information has been entered and whether it meets the validation rules.

### 2. Username Validation

The username must:

- Contain an underscore (`_`).
- Be no longer than five characters.

Example: `kyl_1`

### 3. Password Validation

The password must:

- Contain at least eight characters.
- Include at least one uppercase letter.
- Include at least one number.
- Include at least one special character.

Example: `Ch&&sec@ke99!`

### 4. Cellphone Number Validation

The cellphone number must:

- Begin with `+27`.
- Contain the correct number of digits.
- Follow the required South African international format.

Example: `+27838968976`

### 5. User Login

After successful registration, the user enters their username and password.

The application compares the entered credentials with the registered details.

If the credentials match, a successful login message is displayed. Otherwise, an error message appears.

## JUnit Testing

Part 1 includes `LoginTest.java`, which tests:

- Valid and invalid usernames
- Valid and invalid passwords
- Valid and invalid cellphone numbers
- Successful registration
- Successful login
- Login status messages

The original Part 1 implementation included nine JUnit tests.

## How to Run the Application

1. Open the Maven project in Apache NetBeans.
2. Allow Maven to load the required dependencies.
3. Run `Main.java`.
4. Enter the registration details.
5. Log in using the registered credentials.

## Running the Tests

1. Open the project in NetBeans.
2. Expand **Test Packages**.
3. Locate `LoginTest.java`.
4. Right-click and select **Test File**.

A successful test run should show zero failures and errors, followed by `BUILD SUCCESS`.

## Requirements

- Java Development Kit (JDK)
- Apache Maven
- Apache NetBeans
- JUnit 5

## Regular Expression Reference

Cellphone number validation uses a regular expression to check the South African international format.

```regex
^\+27[0-9]{9}$
```

Example: `+27838968976`

## References

Oracle. n.d. *Java Documentation*. Available at: https://docs.oracle.com/en/java/

Apache Maven. n.d. *Maven Documentation*. Available at: https://maven.apache.org/guides/

ICASA. n.d. *Numbering Plan Regulations*. Available at: https://www.icasa.org.za/

JUnit. n.d. *JUnit 5 User Guide*. Available at: https://junit.org/junit5/docs/current/user-guide/

**YouTube Reference:**  
https://youtube.com/shorts/otCD6NTPzio?si=s_WrawHZwDAlZ4Be

---

# Part 2: Message Management and JSON Storage

## Project Overview

QuickChat Part 2 extends the registration and login functionality developed in Part 1 by introducing a console-based messaging system.

The application allows users to create messages, validate recipient cellphone numbers, generate unique message identifiers, and choose whether to send, disregard, or store messages.

Stored messages are saved in JSON format.

## Features

- User registration and login
- Menu-driven console interface
- Message limit selection
- Unique 10-digit Message ID generation
- Message ID validation
- Recipient cellphone number validation
- Maximum message length of 250 characters
- Message Hash generation
- Send, disregard, and store message options
- Total messages sent
- JSON message storage
- Automated JUnit testing

## Technologies Used

- Java
- Apache Maven
- Apache NetBeans
- JUnit 5
- Gson for JSON processing
- Git and GitHub
- GitHub Desktop

## Project Structure

The main files used in Part 2 include:

- `Main.java` — Handles the application menu and user interaction.
- `Login.java` — Manages registration and login.
- `Message.java` — Handles message validation, identifiers, hashes, and storage.
- `MessageTest.java` — Tests message functionality.
- `pom.xml` — Manages Maven dependencies and project configuration.
- `storedMessages.json` — Stores messages in JSON format.

## How the Application Works

### 1. Registration and Login

The user registers and logs in using the functionality developed in Part 1.

### 2. Message Limit

After logging in, the user enters the number of messages they wish to process.

The application uses loops to control message entry and processing.

### 3. Message Creation

For each message, the user enters:

- Recipient cellphone number
- Message text

The application validates the recipient and checks that the message does not exceed 250 characters.

### 4. Message ID Generation

Each message receives a randomly generated 10-digit Message ID.

The Message ID is used to identify individual messages.

### 5. Message Hash Generation

The application generates a Message Hash using the first two digits of the Message ID, the message number, and selected words from the message.

The hash helps identify messages during processing.

### 6. Message Actions

The user can choose to:

- **Send Message** — Mark the message as successfully sent.
- **Disregard Message** — Discard the message after confirmation.
- **Store Message** — Save the message in JSON format.

### 7. Total Messages Sent

The application keeps track of the number of messages successfully sent.

## Part 2 Main Menu

The original Part 2 application included:

1. Send Messages
2. Show recently sent messages
3. Quit

The recently sent messages option initially displayed a "Coming Soon" message, as specified in the Part 2 requirements.

This functionality was subsequently extended in Part 3.

## JSON Message Storage

Messages selected for storage are saved in a file named `storedMessages.json`.

The file contains message information in JSON format, including message identifiers, recipient details, message text, hashes, and status.

## How to Run the Application

1. Clone or download the GitHub repository.
2. Open the Maven project in Apache NetBeans.
3. Allow Maven to load the project dependencies.
4. Run `Main.java`.
5. Register and log in.
6. Enter the number of messages to process.
7. Follow the menu instructions to send, disregard, or store messages.

## JUnit Testing

Part 2 includes automated tests that verify message functionality and related requirements.

To run the tests:

1. Open the project in NetBeans.
2. Expand **Test Packages**.
3. Locate the relevant test class.
4. Right-click and select **Test File**, or run all project tests.

A successful test run should display zero failures and errors, followed by `BUILD SUCCESS`.

## Version Control

Git and GitHub are used to manage the project's source code and development history.

GitHub Desktop is used to commit and push changes to the remote repository.

## Requirements

- Java Development Kit (JDK)
- Apache Maven
- Apache NetBeans or another Java IDE with Maven support
- JUnit 5
- Gson

## YouTube Reference

https://youtu.be/cIv5deuFNdg?si=p3eSgmTlyDH-MJBA

---

# Part 3: Store Data and Display Message Reports

*Continue with your existing Part 3 README section here.*

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

