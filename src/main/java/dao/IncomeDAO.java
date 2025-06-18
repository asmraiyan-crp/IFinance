package dao;
import model.Income;

import java.util.List;

public interface IncomeDAO {
    void addincome(Income income);
    List<Income> getAllIncome();
    Income getIncomeById(int id);
    void updateIncome(int id);
    void deleteIncome(int id);
}
