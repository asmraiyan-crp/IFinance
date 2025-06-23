import dao.*;
import db.DBConnection;
import model.Income;
import java.util.List;
import java.sql.Connection;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DBConnection.initializeDatabase();
        try(Connection conn = DBConnection.getConnection()){
            IncomeDAO incomedao = new IncomeDAOImplement(conn);
            System.out.println("Enter the amount: ");
            double amount = sc.nextDouble();sc.nextLine();
            System.out.println("Enter the source: ");
            String source = sc.nextLine();
            LocalDate date = LocalDate.now();
            Income income = new Income(amount,source,date);
            incomedao.addincome(income);
            incomedao.deleteIncome(2);
            List<Income> allincome = incomedao.getAllIncome();
            for( Income inc : allincome){
                System.out.println(inc);
            }
            System.out.println("Enter the date you want see Year->Month->Day");
            int y = sc.nextInt();int m =sc.nextInt();int d = sc.nextInt();
            List<Income> inc = incomedao.getIncomeBydate(y,m,d);
            for(Income in : inc){
            System.out.println(in);}

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}