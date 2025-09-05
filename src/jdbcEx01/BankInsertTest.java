package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankInsertTest {
    public static void main(String[] args) {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String username = "root";
        String password = "mysql1234";

        try {
            Class.forName(driverName); //class.forname으로 동적 로드하고
            System.out.println("Driver loaded OK!");//성공하면 Driver loaded 출력
        } catch (Exception e) {// 실패했다면
            System.out.println("Driver loaded failed!");

        }
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            // 1. INSERT 쿼리 실행
            String insertSql = "INSERT INTO account(ano, owner, balance) VALUES (?, ?, ?)";


            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                // INSERT할 값 지정
                pstmt.setString(1, "1111-1212-3232-12121");
                pstmt.setString(2, "홍길동");
                pstmt.setInt(3, 100000);

                int result = pstmt.executeUpdate();
                if (result == 1) {
                    System.out.println("계좌가 성공적으로 개설되었습니다.");
                }
            }

            // 2. 방금 INSERT한 데이터가 잘 들어갔는지 확인하기 위한 SELECT 쿼리 실행
            String selectSql = "SELECT ano, owner, balance FROM account WHERE ano = ?";

            try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                // 조회할 계좌번호 지정
                pstmt.setString(1, "1111-1212-3232-12121");

                // SELECT 쿼리 실행 후 결과 받기
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) { // 결과가 있다면
                        // 요구사항 형식에 맞춰 결과 출력
                        System.out.println("========결과========");
                        System.out.println("계좌번호: " + rs.getString("ano"));
                        System.out.println("계좌주: " + rs.getString("owner"));
                        System.out.println("계좌총액: " + rs.getInt("balance") + " 원");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("데이터베이스 연결 또는 처리 중 오류 발생: " + e);
        }
    }
}