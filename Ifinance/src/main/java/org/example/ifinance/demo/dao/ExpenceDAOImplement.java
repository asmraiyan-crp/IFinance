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
    public void addExpenceLoan(Expence expence) {
        String sql = "insert into loan (date,amount) values (?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
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
    public List<Expence> getAllExpenceOfTransport() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from transport";
        try(PreparedStatement prept = conn.prepareStatement(sql);
        ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }
    @Override
    public List<Expence> getAllExpenceOfEducation() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from education";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
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

    @Override
    public List<Expence> getAllExpenceOfFood() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from food";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }
    @Override
    public List<Expence> getAllExpenceOfTour() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from tour";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }
    @Override
    public List<Expence> getAllExpenceOfLoan() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from loan";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }
    @Override
    public List<Expence> getAllExpenceOfRefreshment() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from refreshment";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }
    @Override
    public List<Expence> getAllExpenceOfHousehold() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from household";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }
    @Override
    public List<Expence> getAllExpenceOfOthers() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from others";
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setDate(rs.getString("date"));
                exp.setAmount(rs.getDouble("amount"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }


    @Override
    public List<Expence> getExpenceOfTransportByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from transport where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfEducationByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from Education where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfFoodByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from food where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfTourByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from tour where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfRefreshmentByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from refreshment where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfLoanByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from loan where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfHouseholdByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from household where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    @Override
    public List<Expence> getExpenceOfOthersByDate(int year,int month,int day) {
        List<Expence> list = new ArrayList<>();
        String date = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "select * from others where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setDate(rs.getString("date"));
            exp.setAmount(rs.getDouble("amount"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }
    public void deleteEducationExpence(int id){
        String sql = "delete from education where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteFoodExpence(int id){
        String sql = "delete from food where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteTourExpence(int id){
        String sql = "delete from tour where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteRefreshmentExpence(int id){
        String sql = "delete from Refreshment where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteLoanExpence(int id){
        String sql = "delete from loan where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteHouseholdExpence(int id){
        String sql = "delete from household where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteOthersExpence(int id){
        String sql = "delete from others where id =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1, id);
            prept.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
