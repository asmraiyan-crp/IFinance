package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.*;
import model.Expence;
import model.Income;

public abstract class ExpenceDAOImplement  implements ExpenceDAO{
    private Connection conn;
    public ExpenceDAOImplement(Connection conn){
        this.conn = conn;
    }

    @Override
    public void addExpenceTransport(Expence expence) {
        String sql = "insert into transport (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.setString(3,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceEducation(Expence expence) {
        String sql = "insert into education (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.setString(3,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceFood(Expence expence) {
        String sql = "insert into food (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.setString(3,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceTour(Expence expence) {
        String sql = "insert into tour (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.setString(3,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceRefreshment(Expence expence) {
        String sql = "insert into refreshment (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)) {
            prestat.setString(1, expence.getDate());
            prestat.setDouble(2, expence.getAmount());
            prestat.setString(3, expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceLoan(Expence expence) {
        String sql = "insert into loan (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.setString(3,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceHousehold(Expence expence) {
        String sql = "insert into household (date,amount,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
                prestat.setString(1,expence.getDate());
                prestat.setDouble(2,expence.getAmount());
                prestat.setString(3,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addExpenceOthers(Expence expence) {
        String sql = "insert into others (date,amount ,note) values (?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setString(1,expence.getDate());
            prestat.setDouble(2,expence.getAmount());
            prestat.setString(3,expence.getNote());
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
                exp.setNote(rs.getString("note"));
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
                exp.setNote(rs.getString("note"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
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
                exp.setNote(rs.getString("note"));
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
                exp.setNote(rs.getString("note"));
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
                exp.setNote(rs.getString("note"));
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
                exp.setNote(rs.getString("note"));
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
                exp.setNote(rs.getString("note"));
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
                exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
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
            exp.setNote(rs.getString("note"));
            list.add(exp);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;

    }

    @Override
    public void updateTransportExpence(Expence expence) {
       String sql = "update transport set date=?, amount = ? , note = ? where id = ?";
       try(PreparedStatement prept = conn.prepareStatement(sql)){
           prept.setString(1, expence.getDate());
           prept.setDouble(2,expence.getAmount());
           prept.setString(3,expence.getNote());
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
    @Override
    public void updateEducationExpence(Expence expence) {
        String sql = "update education set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateFoodExpence(Expence expence) {
        String sql = "update food set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateTourExpence(Expence expence) {
        String sql = "update tour set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateRefreshmentExpence(Expence expence) {
        String sql = "update refreshment set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateLoanExpence(Expence expence) {
        String sql = "update loan set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateHouseholdExpence(Expence expence) {
        String sql = "update household set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateOthersExpence(Expence expence) {
        String sql = "update others set date=?, amount = ? , note = ? where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1, expence.getDate());
            prept.setDouble(2,expence.getAmount());
            prept.setString(3,expence.getNote());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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
