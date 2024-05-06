package src.bookstore.admindmin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Method to establish database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to insert user details into the database
    public static void insertUser(String userId, String firstName, String lastName,
                                  String email, String phoneNumber, String password) {
        String query = "INSERT INTO users (userId, firstName, lastName, email, phoneNumber, password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, password);

            // Execute the query
            preparedStatement.executeUpdate();

            // Close the connection and statement
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}