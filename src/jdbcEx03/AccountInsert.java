package jdbcEx03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/// READ
public class AccountInsert {
    public static void main(String[] args) {

        // DB 연결 정보
        String driverName = "com.mysql.cj.jdbc.Driver";  //이름 설정해주고
        String url = "jdbc:mysql://localhost:3306/bankdb?serverTimezone=Asia/Seoul";//url설정해주고
        String username = "root"; //이름 설정해주고
        String password = "mysql1234"; // 본인 비밀번호로 수정

        // 조회할 계좌번호
        String searchAccountNumber = "110-456-789012";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println(" 드라이버 로딩 실패!");
            return;
        }

        // accounts와 customers 테이블을 JOIN하여 계좌 정보와 소유주 이름을 함께 가져옵니다.
        String sql = "SELECT a.account_number, a.account_type, a.balance, a.open_date, " +
                "c.first_name, c.last_name " +
                "FROM accounts a " +
                "JOIN customers c ON a.customer_id = c.customer_id " +
                "WHERE a.account_number = ?";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            System.out.println(" 데이터베이스 연결 성공!");

            // SQL의 '?' 파라미터에 조회할 계좌번호를 설정합니다.
            pstmt.setString(1, searchAccountNumber);

            // SELECT 문 실행
            try (ResultSet rs = pstmt.executeQuery()) {
                // 결과가 있다면 (해당 계좌번호가 존재한다면)
                if (rs.next()) {
                    System.out.println("---  계좌 조회 결과 ---");
                    System.out.println("계좌번호: " + rs.getString("account_number"));
                    System.out.println("예금주: " + rs.getString("last_name") + rs.getString("first_name"));
                    System.out.println("계좌종류: " + rs.getString("account_type"));
                    System.out.println("잔액: " + rs.getBigDecimal("balance"));
                    System.out.println("개설일: " + rs.getTimestamp("open_date"));
                    System.out.println("------------------------");
                } else {
                    System.out.println(" [" + searchAccountNumber + "] 계좌를 찾을 수 없습니다.");
                }
            }

        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류 발생: " + e.getMessage());
        }
    }
}