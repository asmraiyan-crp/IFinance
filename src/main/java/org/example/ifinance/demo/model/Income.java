package org.example.ifinance.demo.model;
import java.time.LocalDate;
public class Income {
    private int id;
    private double amount;
    private String  date;

    public Income(){}
    public Income(int id, double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date.toString();
    }

    public Income(double amount, String date) {
        this.id = 0;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDate(LocalDate date) {
        this.date = date.toString();
    }
    public  void setDate(String date){
        this.date = date;
    }
    public String toString() {
        return "Income{id=" + id + ", amount=" + amount + ", source='" + "', date='" + date + "'}";
    }
}
