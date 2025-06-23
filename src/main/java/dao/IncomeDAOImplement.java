package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.*;
import model.Income;

public  class IncomeDAOImplement implements IncomeDAO{
    private Connection conn;
    public IncomeDAOImplement(Connection conn){
        this.conn = conn;
    }

    @Override
    public void addincome(Income income) {
        String sql = "insert into income (amount,source,date) values(?,?,?)";
        try(PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setDouble(1,income.getAmount());
            prestat.setString(2, income.getSource());
            prestat.setString(3,income.getDate());
            prestat.executeUpdate();
            System.out.println("Updated Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Income> getAllIncome(){
        List<Income> incomelist = new ArrayList<>();
        String sql = "select * from income";
        try(Statement st = conn.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                Income income = new Income();
                income.setAmount(rs.getDouble("amount"));
                income.setSource(rs.getString("source"));
                income.setDate(rs.getString("date"));

            income.setId(rs.getInt("id"));
            incomelist.add(income);}
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return incomelist;
    }

    @Override
    public List<Income> getIncomeBydate(int year, int month, int day) {
        List<Income> list = new ArrayList<>();
        String formattedDate = String.format("%04d-%02d-%02d", year, month, day);
        String sql = "SELECT * FROM income WHERE date = ?";
        try (PreparedStatement prept = conn.prepareStatement(sql)) {
            prept.setString(1, formattedDate);
            ResultSet rs = prept.executeQuery();
            while (rs.next()) {
                Income income = new Income();
                income.setId(rs.getInt("id"));
                income.setAmount(rs.getDouble("amount"));
                income.setSource(rs.getString("source"));
                income.setDate(rs.getString("date"));
                list.add(income);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void updateIncome(Income income) {
        String sql = "UPDATE income SET amount = ?, source = ?, date = ? WHERE id = ?";
        try (PreparedStatement prept = conn.prepareStatement(sql)) {
            prept.setDouble(1, income.getAmount());
            prept.setString(2, income.getSource());
            prept.setString(3, income.getDate());
            prept.setInt(4, income.getId());
            prept.executeUpdate();
            System.out.println("Income updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    @Override
    public void deleteIncome(int id) {
        String sql = "DELETE FROM income WHERE id = ?";
        try (PreparedStatement prept = conn.prepareStatement(sql)) {
            prept.setInt(1, id);
            prept.executeUpdate();
            System.out.println("Income deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
