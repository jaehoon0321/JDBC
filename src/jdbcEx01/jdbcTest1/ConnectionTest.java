package jdbcEx01.jdbcTest1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionTest {
    public static void main(String[] args) {
        String DriverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String username = "root";
        String password = "mysql1234";

        try {
            Class.forName(DriverName);
            System.out.println("Driver Loaded OK!");
        } catch (Exception e) {
            System.out.println("Driver Loaded failed!");
        }

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            System.out.println("AutoCommit 상태: " + con.getAutoCommit());
            con.setAutoCommit(true);

            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate("INSERT INTO person(id, name) VALUES(100, '홍길동')");
            if (result == 1) {
                System.out.println("Insert successful!");
            }
        } catch (Exception e) {
            System.out.println("Connection established! " + e);
        }
    }
}
