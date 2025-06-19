import dao.*;
import db.DBConnection;
import model.Income;
import java.util.List;
import java.sql.Connection;
import java.util.Scanner;

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
            System.out.println("Enter the date: ");
            String date = sc.nextLine();
            Income income = new Income(amount,source,date);
            incomedao.addincome(income);
            incomedao.deleteIncome(2);
            List<Income> allincome = incomedao.getAllIncome();
            for( Income inc : allincome){
                System.out.println(inc);
            }


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}