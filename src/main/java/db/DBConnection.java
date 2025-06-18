package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/finance_app";
    private static final String USER = "root";           // <-- your MySQL username
    private static final String PASSWORD = "@smsql";   // <-- your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
