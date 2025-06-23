package utils;
import  java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
public class Monthly_Smmery {
    private static double gettotal(Connection conn,String table){
        String sql = "select sum(amount) as total from "+table;
        try(PreparedStatement prept = conn.prepareStatement(sql);
            ResultSet rs = prept.executeQuery()){
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static void monthySummeryandReset(Connection conn){
        LocalDate today = LocalDate.now();
        if(today.getDayOfMonth()!=1) return;
        YearMonth prevmonth = YearMonth.from(today.minusMonths(1));
        String yearMonth = prevmonth.toString();
        

    }
}
