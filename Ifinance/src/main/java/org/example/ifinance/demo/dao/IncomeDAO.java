package org.example.ifinance.demo.dao;
import org.example.ifinance.demo.model.Income;

import java.util.List;

public interface IncomeDAO {
    void addincome(Income income);
    double getTotalIncomeByMonth(int year, int month);

}