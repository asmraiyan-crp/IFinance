package org.example.ifinance.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";         // <-- your MySQL username
    private static final String PASSWORD = "root";   // <-- your MySQL password
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
                                "date VARCHAR(50))"
                );

                // Create transport table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS transport (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), "+
                                "amount DOUBLE NOT NULL, " +
                                "note TEXT)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS transport_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL, " +
                                "note TEXT)"
                );
                // Create education table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS education (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL" +
                                ")"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS education_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL" +
                                ")"
                );
                //Create food table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS food (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS food_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL)"
                );
                //Create tour table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS tour (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS tour_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL)"
                );
                //Create household table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS household (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS household_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL)"
                );
                //Create refreshment table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS refreshment (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS refreshment_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL)"
                );
                //Create loan table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS loan (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS loan_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL)"
                );
                //Create others table
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS others (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "date VARCHAR(50), " +
                                "amount DOUBLE NOT NULL)"
                );dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS others_m (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "amount DOUBLE NOT NULL)"
                );
                dbStmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS monthly_summery (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                "year INT NOT NULL, " +
                                "month INT NOT NULL, " +
                                "income DOUBLE NOT NULL, " +
                                "expence DOUBLE NOT NULL, " +
                                "savings DOUBLE NOT NULL)"
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

