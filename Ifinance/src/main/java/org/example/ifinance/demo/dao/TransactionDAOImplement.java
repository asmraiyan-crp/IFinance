package org.example.ifinance.demo.dao;

import org.example.ifinance.demo.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAOImplement  implements TransactionDAO{
    private Connection conn;
    public TransactionDAOImplement(Connection conn){
        this.conn = conn;
    }
    @Override
    public void deleteTransactionFromDatabase(Transaction t) {
        String sql = "DELETE FROM " + t.getTable() + " WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, t.getId());
            st.executeUpdate();
            System.out.println("Deleted from database: " + t.getTable() + ", id=" + t.getId());
        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
