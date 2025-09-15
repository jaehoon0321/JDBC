package callableStatementEx;

import util.DBUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;


public class callableStatement {

    /// 전체 회원들의 리스트를 출력하는 기능 구현.
    static Connection conn = util.DBUtil.getConnection();

    public static void main(String[] args) {


        String SQL = "{CALL SP_MEMBER_LIST()}";

        try(ResultSet rs = conn.prepareStatement(sql)){
            System.out.println("-----------------------------");
            System.out.println("----------회원목록-------------");
            System.out.println("-----------------------------");
            System.out.printf("%-3s | %-50s | %-40s | %-13s | %-19s%n",
                    "순서", "아이디", "이메일", "전화번호", "가입일");
            System.out.println("-----------------------------");


            while (rs.next()) //rs 의다음행이 존재할때까지 반복해주고







        }

        }

    }