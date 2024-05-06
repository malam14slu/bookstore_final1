package src.bookstore.admin;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.bookstore.admindmin.DatabaseManager;

public class UserRegistration {
    // Method to register a new user
    public static void registerUser(String userId, String firstName, String lastName,
                                    String email, String phoneNumber, String password) {
        // Perform registration and store user information in the database
        DatabaseManager.insertUser(userId, firstName, lastName, email, phoneNumber, password);

        // Confirmation message
        JOptionPane.showMessageDialog(null, "User Registration Successful");
    }

    // Method to display registration form
    public static void showRegistrationForm() {
        // Create a JPanel to hold the form components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to vertical

        // Create input fields for each attribute
        JTextField userIdField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField phoneNumberField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20); // Use JPasswordField for password

        // Add labels and input fields to the panel
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        // Show input dialog with the panel
        int result = JOptionPane.showConfirmDialog(null, panel, "User Registration", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // If OK is clicked, retrieve values from input fields
            String userId = userIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            // Get password securely
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Perform validation
            if (!userId.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() &&
                !email.isEmpty() && !phoneNumber.isEmpty() && !password.isEmpty()) {
                // If all fields are provided, register the user
                registerUser(userId, firstName, lastName, email, phoneNumber, password);
            } else {
                // Show error message if any field is empty
                JOptionPane.showMessageDialog(null, "All fields are required");
            }

            // Clear the password field after use for security
            passwordField.setText("");
        }
    }
}