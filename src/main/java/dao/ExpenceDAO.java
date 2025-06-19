package dao;

import model.Expence;

import java.util.List;

public interface ExpenceDAO {
    void addExpence(Expence expence);
    List<Expence> getAllExpence();
    Expence getExpenceBydate(String date);
    void updateExpence(Expence income);
    void deleteExpence(int id);
}
