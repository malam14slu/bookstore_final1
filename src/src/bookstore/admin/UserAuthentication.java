package src.bookstore.admin;


import javax.swing.JOptionPane;

public class UserAuthentication {
    // Method to authenticate user
    public static boolean authenticateUser(String adminId, String adminpassword) {
        // Perform authentication based on provided userId and password
        if ((adminId.equals("admin1") && adminpassword.equals("adminpassword1")) ||
            (adminId.equals("admin2") && adminpassword.equals("adminpassword2"))) {
            return true; // Authentication successful
        } else {
            return false; // Authentication failed
        }
    }
    
    // Method to display authentication dialog
    public static boolean showAuthenticationDialog() {
        String adminId = JOptionPane.showInputDialog("Enter Admin ID:");
        String adminpassword = JOptionPane.showInputDialog("Enter Admin Password:");

        return authenticateUser(adminId,adminpassword );
    }
}

