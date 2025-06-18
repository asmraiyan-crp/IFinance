package maedel;
public class Expence {
  private int id;
  private double amount;
  private String source;
  private String  date;
  public Expence (int id,double amount, String source,String date){
    this.id = id;
    this.amount = amount;
    this.source = source;
    this.date = date;
  }
  public Eexpence (double amount , String source,String date){
    this(0,amount, source,date)
  }
  //getters and setters
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
}
  
