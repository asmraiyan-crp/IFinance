package org.example.ifinance.demo.dao;

import java.sql.*;
import java.time.LocalDate;

public class SavingsDAO {
    private Connection conn;

    public SavingsDAO(Connection conn) {
        this.conn = conn;
    }

    public double calculateSavings() {
        double totalSavings = 0;

        // ✅ Include past months' saved values from the monthly summary
        totalSavings += getTotal(conn, "monthly_summery", "savings");

        // ✅ Calculate current month's income and expenses
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        double currentIncome = getMonthlyTotal("income", "amount", currentYear, currentMonth);

        String[] expenseTables = {
                "transport", "education", "food", "tour",
                "refreshment", "loan", "household", "others"
        };

        double currentExpenses = 0;
        for (String table : expenseTables) {
            currentExpenses += getMonthlyTotal(table, "amount", currentYear, currentMonth);
        }

        double currentNetSavings = currentIncome - currentExpenses;
        totalSavings += currentNetSavings;

        return totalSavings;
    }

    // Helper to get total sum from any table+column
    private static double getTotal(Connection conn, String table, String column) {
        String sql = "SELECT SUM(" + column + ") AS total FROM " + table;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println("Error in getTotal for " + table + ": " + e.getMessage());
        }
        return 0;
    }

    // Helper to get current month's total from a table
    private double getMonthlyTotal(String table, String column, int year, int month) {
        String sql = "SELECT SUM(" + column + ") AS total FROM " + table +
                " WHERE YEAR(STR_TO_DATE(date, '%Y-%m-%d')) = ? AND MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println("Error in getMonthlyTotal for " + table + ": " + e.getMessage());
        }
        return 0;
    }
}