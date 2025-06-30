package org.example.ifinance.demo.dao;

import org.example.ifinance.demo.model.Transaction;

public interface TransactionDAO {
    public void deleteTransactionFromDatabase(Transaction t);
}
