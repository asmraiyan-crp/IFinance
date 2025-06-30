package org.example.ifinance.demo.dao;

import org.example.ifinance.demo.model.Income;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAOImplement implements IncomeDAO {
    private Connection conn;

    public IncomeDAOImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addincome(Income income) {
        String sql = "INSERT INTO income (amount, date) VALUES (?, ?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)) {
            prestat.setDouble(1, income.getAmount());
            prestat.setString(2, income.getDate());
            prestat.executeUpdate();
            System.out.println("Income added successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting income: " + e.getMessage());
        }
    }

    @Override
    public double getTotalIncomeByMonth(int year, int month) {
        String sql =
                "SELECT SUM(amount) AS total FROM income " +
                        "WHERE YEAR(STR_TO_DATE(date, '%Y-%m-%d')) = ? " +
                        "  AND MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching monthly income: " + e.getMessage());
        }
        return 0;
    }

}