package callableStatementEx;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MemberInsert {

    static  Connection conn = util.DBUtil.getConnection();// bookmarket db와 연동


    public static void main(String[] args) {

        String m_userid = "jenny";
        String m_pwd = "jenny1234";
        String m_email = "jenny@gmail.com";
        String m_hp = "010-1234-1234";
        String resultString = null;
        String sql = "{CALL SP_MEMBER_INSERT(?,?,?,?,?)}"; // 프로시저를 부를게 값을 맞춰줘.

        try(CallableStatement call = conn.prepareCall(sql))// 그 값을 담아서
        {
            //IN 파라미터 셋팅
            call.setString(1,m_userid); //맞춰 주지 않아도 됨.
            call.setString(2,m_pwd);
            call.setString(3,m_email);
            call.setString(4,m_hp);

            //OUT 파라미터 셋팅
            call.registerOutParameter(5,java.sql.Types.INTEGER);

            //실행
            call.execute();

            int rtn = call.getInt(5);// 5번을 받아옴

            if(rtn == 100){
                //  conn.rollback();
                System.out.println("이미 가입된 사용자입니다.");
            } else{
                // conn.commit();
                System.out.println("회원 가입이 되었습니다. 감사합니다.");
            }
        } catch (SQLException e) {
//            try{conn.rollback();} catch (SQLException ex) {
//                ex.printStackTrace();
//            }

        }

    }
}