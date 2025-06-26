package org.example.ifinance.demo.dao;

import org.example.ifinance.demo.model.Expence;

import java.util.List;

public interface ExpenceDAO {
    void addExpenceTransport(Expence expence);
    void addExpenceEducation(Expence expence);
    void addExpenceFood(Expence expence);
    void addExpenceTour(Expence expence);
    void addExpenceRefreshment(Expence expence);
    void addExpenceLoan(Expence expence);
    void addExpenceHousehold(Expence expence);
    void addExpenceOthers(Expence expence);
    // void addExpence(Expence expence);
    List<Expence> getAllExpenceOfTransport();
    List<Expence> getAllExpenceOfEducation();
    List<Expence> getAllExpenceOfFood();
    List<Expence> getAllExpenceOfTour();
    List<Expence> getAllExpenceOfRefreshment();
    List<Expence> getAllExpenceOfLoan();
    List<Expence> getAllExpenceOfHousehold();
    List<Expence> getAllExpenceOfOthers();
    List<Expence> getExpenceOfTransportByDate(int year,int month,int day);
    List<Expence> getExpenceOfEducationByDate(int year,int month,int day);
    List<Expence> getExpenceOfFoodByDate(int year,int month,int day);
    List<Expence> getExpenceOfTourByDate(int year,int month,int day);
    List<Expence> getExpenceOfRefreshmentByDate(int year,int month,int day);
    List<Expence> getExpenceOfLoanByDate(int year,int month,int day);
    List<Expence> getExpenceOfHouseholdByDate(int year,int month,int day);
    List<Expence> getExpenceOfOthersByDate(int year,int month,int day);
//    void updateTransportExpence(Expence expence);
//    void updateEducationExpence(Expence expence);
//    void updateFoodExpence(Expence expence);
//    void updateTourExpence(Expence expence);
//    void updateRefreshmentExpence(Expence expence);
//    void updateLoanExpence(Expence expence);
//    void updateHouseholdExpence(Expence expence);
//    void updateOthersExpence(Expence expence);
//    void deleteTransportExpence(int id);
    void deleteEducationExpence(int id);
    void deleteFoodExpence(int id);
    void deleteTourExpence(int id);
    void deleteRefreshmentExpence(int id);
    void deleteLoanExpence(int id);
    void deleteHouseholdExpence(int id);
    void deleteOthersExpence(int id);
}
