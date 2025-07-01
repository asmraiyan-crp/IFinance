package org.example.ifinance.demo.dao;

import org.example.ifinance.demo.model.Expence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenceDAOImplement  implements ExpenceDAO{
    private Connection conn;
    public ExpenceDAOImplement(Connection conn){
        this.conn = conn;
    }

    @Override
    public void addExpenceTransport(Expence expence) {
        String sql = "insert into transport (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceEducation(Expence expence) {
        String sql = "INSERT INTO education (date, amount) VALUES (?, ?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)) {
            prestat.setString(1, expence.getDate());
            prestat.setDouble(2, expence.getAmount());
            prestat.executeUpdate(); // ✅ this actually runs the insert
            System.out.println("Expense inserted successfully");
        } catch (Exception e) {
            System.out.println("Error inserting expense: " + e.getMessage());
        }
    }

    @Override
    public void addExpenceFood(Expence expence) {
        String sql = "insert into food (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceTour(Expence expence) {
        String sql = "insert into tour (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceRefreshment(Expence expence) {
        String sql = "insert into refreshment (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)) {
            prestat.setString(1, expence.getDate());
            prestat.setDouble(2, expence.getAmount());
            prestat.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceHousehold(Expence expence) {
        String sql = "insert into household (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
                prestat.setString(1,expence.getDate());
                prestat.setDouble(2,expence.getAmount());
                prestat.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceOthers(Expence expence) {
        String sql = "insert into others (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public double getTotalExpenseByCategoryAndMonth(String category, int year, int month) {
        String sql = "select amount as total from "+category+" where year = ? and month = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching expense total for " + category + ": " + e.getMessage());
        }
        return 0;
    }
    @Override
    public double getTotalExpenseByCategoryAndMonthcurr(String category, int year, int month) {
        String sql = String.format(
                "SELECT SUM(amount) AS total FROM %s " +
                        "WHERE YEAR(STR_TO_DATE(date, '%%Y-%%m-%%d')) = ? " +
                        "  AND MONTH(STR_TO_DATE(date, '%%Y-%%m-%%d')) = ?",
                category.toLowerCase()
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching expense total for " + category + ": " + e.getMessage());
        }
        return 0;
    }
}
