package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.*;
import model.Expence;

public abstract class ExpenceDAOImplement  implements ExpenceDAO{
    private Connection conn;
    public ExpenceDAOImplement(Connection conn){
        this.conn = conn;
    }

    @Override
    public void addExpence(Expence expence) {
        String sql = "insert into expense (amount ,category, date,note) values (?,?,?,?)";
        try (PreparedStatement prestat = conn.prepareStatement(sql)){
            prestat.setDouble(1,expence.getAmount());
            prestat.setString(2,expence.getCategory());
            prestat.setString(3,expence.getDate());
            prestat.setString(4,expence.getNote());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Expence> getAllExpence() {
        List<Expence> expencelist = new ArrayList<>();
        String sql = "select * from expense";
        try(PreparedStatement prept = conn.prepareStatement(sql);
        ResultSet rs = prept.executeQuery()) {
            while (rs.next()) {
                Expence exp = new Expence();
                exp.setId(rs.getInt("id"));
                exp.setAmount(rs.getDouble("amount"));
                exp.setCategory(rs.getString("category"));
                exp.setDate(rs.getString("date"));
                exp.setNote(rs.getString("note"));
                expencelist.add(exp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expencelist;
    }

    @Override
    public Expence getExpenceBydate(String date) {
        String sql = "select * from expense where date =?";
        try(PreparedStatement prept = conn.prepareStatement(sql)){
            prept.setString(1,date);
            ResultSet rs = prept.executeQuery();
            Expence exp = new Expence();
            exp.setId(rs.getInt("id"));
            exp.setAmount(rs.getDouble("amount"));
            exp.setCategory(rs.getString("category"));
            exp.setDate(rs.getString("date"));
            exp.setNote(rs.getString("note"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    //@Override
   // public void updateExpence(Expence income) {
     //   String sql = "update expense set amount = ? , category = ?,"
    //}
}
