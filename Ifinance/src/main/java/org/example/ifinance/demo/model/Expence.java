<<<<<<< HEAD
package org.example.ifinance.demo.model;
public class Expence {
    private int id;
    private double amount;
    private String category;
    private String  date;
    private String note;
    public  Expence(){}
    public Expence (int id,double amount, String category,String date,String note){
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    public Expence (double amount , String category,String date,String note){
        this(0,amount, category,date,note);
    }
    //getters and setters
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public String getCategory() {
        return category;
    }
    public String getDate() {
        return date;
    }
    public String getNote(){
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCategory(String source) {
        this.category = source;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
=======
package org.example.ifinance.demo.model;
public class Expence {
    private int id;
    private double amount;
    private String category;
    private String  date;
    private String note;
    public  Expence(){}
    public Expence (int id,double amount, String category,String date,String note){
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    public Expence (double amount , String category,String date,String note){
        this(0,amount, category,date,note);
    }
    //getters and setters
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public String getCategory() {
        return category;
    }
    public String getDate() {
        return date;
    }
    public String getNote(){
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCategory(String source) {
        this.category = source;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
>>>>>>> 199172e66ada4434ad023cfcadd89f95c41dd62b
