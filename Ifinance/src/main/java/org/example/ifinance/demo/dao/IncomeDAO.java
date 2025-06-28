<<<<<<< HEAD
package org.example.ifinance.demo.dao;
import org.example.ifinance.demo.model.Income;

import java.util.List;

public interface IncomeDAO {
    void addincome(Income income);
    List<Income> getAllIncome();
    List<Income> getIncomeBydate(int year,int month,int day);
    void updateIncome(Income income);
    void deleteIncome(int id);
    /**
     * Returns the total income in the specified year & month.
     */
    double getTotalIncomeByMonth(int year, int month);

}
=======
package org.example.ifinance.demo.dao;
import org.example.ifinance.demo.model.Income;

import java.util.List;

public interface IncomeDAO {
    void addincome(Income income);
    List<Income> getAllIncome();
    List<Income> getIncomeBydate(int year,int month,int day);
    void updateIncome(Income income);
    void deleteIncome(int id);
    /**
     * Returns the total income in the specified year & month.
     */
    double getTotalIncomeByMonth(int year, int month);

}
>>>>>>> 199172e66ada4434ad023cfcadd89f95c41dd62b
