package org.example.ifinance.demo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.YearMonth;

public class Monthly_Smmery {

    private static double getTotal(Connection conn, String table) {
        String sql = "SELECT SUM(amount) AS total FROM " + table;
        try (PreparedStatement prept = conn.prepareStatement(sql);
             ResultSet rs = prept.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private static void clearAndReset(Connection conn, String table) {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("DELETE FROM " + table);
            st.executeUpdate("ALTER TABLE " + table + " AUTO_INCREMENT = 1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean hasCounted(Connection conn, YearMonth prevMonth) {
        int pMonth = 0;
        int pYear = 0;

        String sql = "SELECT year, month FROM monthly_summery ORDER BY id DESC LIMIT 1";
        try (PreparedStatement prept = conn.prepareStatement(sql);
             ResultSet rs = prept.executeQuery()) {
            if (rs.next()) {
                pYear = rs.getInt("year");
                pMonth = rs.getInt("month");
            } else {
                return false; // No previous summary exists
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return (pYear == prevMonth.getYear() && pMonth == prevMonth.getMonthValue());
    }

    public static void monthlySummaryAndReset(Connection conn) {
        LocalDate today = LocalDate.now();

        YearMonth prevMonth = YearMonth.from(today.minusMonths(1));

        if (hasCounted(conn, prevMonth)) return;

        double totalIncome = getTotal(conn, "income");
        double totalExpense = 0;

        String[] expenseTables = {
                "transport", "education", "food", "tour",
                "refreshment", "loan", "household", "others"
        };

        for (String table : expenseTables) {
            totalExpense += getTotal(conn, table);
        }

        double totalSavings = totalIncome - totalExpense;

        String sql = "INSERT INTO monthly_summery (year, month, income, expence, savings) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement prept = conn.prepareStatement(sql)) {
            prept.setInt(1, prevMonth.getYear());
            prept.setInt(2, prevMonth.getMonthValue());
            prept.setDouble(3, totalIncome);
            prept.setDouble(4, totalExpense);
            prept.setDouble(5, totalSavings);
            prept.executeUpdate();
            System.out.println(" Monthly summary inserted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        clearAndReset(conn, "income");
        for (String table : expenseTables) {
            clearAndReset(conn, table);
        }
    }
}
