package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";         // <-- your MySQL username
    private static final String PASSWORD = "@smsql";   // <-- your MySQL password
    private static final String DB_NAME = "finance_app";

    // Call this method once at startup to create DB + tables if needed
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create database if not exists
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

            // Connect to the database
            try (Connection dbConn = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
                 Statement dbStmt = dbConn.createStatement()) {

                // Create income table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS income (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "amount DOUBLE NOT NULL, " +
                                "source VARCHAR(255), " +
                                "date VARCHAR(50))"
                );

                // Create expense table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS expense (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "amount DOUBLE NOT NULL, " +
                                "category VARCHAR(255), " +
                                "date VARCHAR(50), " +
                                "note TEXT)"
                );

                System.out.println("✅ Database and tables are ready!");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Usual method to get connection to your DB (after init)
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
    }
}
