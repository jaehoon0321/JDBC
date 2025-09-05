package jdbcEx01;

import jdbcEx01.vo.Person;
import util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jdbcEx01.vo.Person;

public class ConnectionDBUtilTest {
    public static void main(String[] args) {
        try {
            Connection con = DBUtil.getConnection();  //도로연결

            Statement stmt = con.createStatement();   //자동차
            /// DBUtil.getConnection()이라는 메서드를 호출해서 데이터베이스와의 연결 통로(Connection)를 만드는작업

            int result = stmt.executeUpdate("INSERT INTO person(id,name) values(1000000,'홍길동11')");
            if (result == 1) {
                System.out.println("Insert successful!");
            }
//저장하고 메소드로 묶어서 재상용 한다

            String selectSql = "select id, name from person";
            ResultSet rs = stmt.executeQuery(selectSql);
            while(rs.next()) {//한행씩 가져와
                jdbcEx01.vo.Person person = new Person();//Person 타입에 담겠다.
                person.setId(rs.getInt(1));//rs로 가져온 값을 담아서 Person setId에 담아서 저장
                person.setName(rs.getString(2));//2번째 이름 가져와서 setName에서 가져오고 출력하겠다
                System.out.println(person.toString());//rs의 정보가 끝날때 까지.
            }

        } catch (Exception e) {
            System.out.println("Connection established!" + e);
        }

    }
}