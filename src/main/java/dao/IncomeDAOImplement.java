package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.*;
import model.Income;

public abstract class IncomeDAOImplement implements IncomeDAO{
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
                Income income = new Income(
                rs.getDouble("amount"),
                rs.getString("source"),
                rs.getString("date"));

            income.setId(rs.getInt("id"));
            incomelist.add(income);}
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return incomelist;
    }

    @Override
    public Income getIncomeById(int id) {
        String sql = "select * from income where id = ?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setInt(1,id);
            ResultSet rs = prept.executeQuery();
            while(rs.next()){
                Income income = new Income(
                        rs.getDouble("amount"),
                        rs.getString("source"),
                        rs.getString("date")
                );
                income.setId(rs.getInt("id"));
                return income;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return null;
        }

    @Override
    public void updateIncome(int id) {
        String sql  = ""
    }
}
