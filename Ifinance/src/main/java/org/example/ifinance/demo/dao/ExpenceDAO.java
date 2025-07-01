
package org.example.ifinance.demo.dao;

import org.example.ifinance.demo.model.Expence;

import java.util.List;

public interface ExpenceDAO {
    void addExpenceTransport(Expence expence);
    void addExpenceEducation(Expence expence);
    void addExpenceFood(Expence expence);
    void addExpenceTour(Expence expence);
    void addExpenceRefreshment(Expence expence);
    void addExpenceHousehold(Expence expence);
    void addExpenceOthers(Expence expence);
double getTotalExpenseByCategoryAndMonth(String category, int year, int month);
    double getTotalExpenseByCategoryAndMonthcurr(String category, int year, int month);
}

