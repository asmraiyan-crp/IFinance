package org.example.ifinance.demo.model;
public class Transaction{
private int id;
private String type;
private String table;
private double amount;
private String date;
public Transaction(){}
    public Transaction(int id, String type, double amount,String date,String table) {
        this.id = id;
        this.type = type;
        this.table = table;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}