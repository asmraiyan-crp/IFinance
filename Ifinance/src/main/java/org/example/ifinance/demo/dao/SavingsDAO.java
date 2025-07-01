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

        totalSavings += getTotal(conn, "monthly_summery", "savings");
        System.out.println("monthly_summary savings "+totalSavings);

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        //double currentIncome = getMonthlyTotal("income", "amount", currentYear, currentMonth);
        double currentIncome = getTotal(conn,"income","amount");

        String[] expenseTables = {
                "transport", "education", "food", "tour",
                "refreshment", "loan", "household", "others"
        };

        double currentExpenses = 0;
        for (String table : expenseTables) {
            //currentExpenses += getMonthlyTotal(table, "amount", currentYear, currentMonth);
            currentExpenses += getTotal(conn,table,"amount");
        }

        double currentNetSavings = currentIncome - currentExpenses;
        totalSavings += currentNetSavings;
        System.out.println("current savings "+ currentNetSavings);

        return totalSavings;
    }

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

}