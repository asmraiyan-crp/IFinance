package dao;
import model.Income;

import java.util.List;

public interface IncomeDAO {
    void addincome(Income income);
    List<Income> getAllIncome();
    Income getIncomeBydate(String date);
    void updateIncome(Income income);
    void deleteIncome(int id);
}
