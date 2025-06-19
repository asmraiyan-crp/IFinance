package model;

public class Income {
    private int id;
    private double amount;
    private String source;
    private String  date;

    public Income(int id, double amount, String source, String date) {
        this.id = id;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    public Income(double amount, String source, String date) {
        this(0, amount, source, date);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public String getSource() {
        return source;
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
    public void setSource(String source) {
        this.source = source;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String toString() {
        return "Income{id=" + id + ", amount=" + amount + ", source='" + source + "', date='" + date + "'}";
    }
}
