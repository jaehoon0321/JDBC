package jdbc_boards.model;

import util.DBUtil;
import jdbc_boards.vo.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    //글 등록 기능
    public boolean createBoard(Board board) {
        String sql = "INSERT INTO boardTable(btitle, bcontent, bwriter, bdate) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, board.getBtitle());//SQL 첫번째 ? btitle
            pstmt.setString(2, board.getBcontent());//SQL 두번째 ? BContente
            pstmt.setString(3, board.getBwriter());//SQL 세번째 ? Bwriter

            int affected = pstmt.executeUpdate(); //데이터베이스에 변경을 요청하는 SQL 쿼리를 실행

            try (ResultSet rs = pstmt.getGeneratedKeys()) {// try 블록이 끝나면 rs가 자동으로 닫히고
                if (rs.next()) {//rs에 다음이 있다면
                    int newbno = rs.getInt(1);//첫번째 있는값을 정수 1로 가져와서
                    board.setBno(newbno);//newbno 에 저장을 하고
                }//if문 닫아주고
            }
            return affected > 0; //0보다 크면 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 전체 글 조회 (최신순으로 정렬)
    public List<Board> selectAll(){
        List<Board> boardList = new ArrayList<>();
        String sql = "SELECT bno, btitle, bcontent, bwriter, bdate FROM boardTable ORDER BY bno DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){
                Board board = new Board();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                // Timestamp를 LocalDateTime으로 변환하여 타입 불일치 해결
                board.setBdate(rs.getTimestamp("bdate").toLocalDateTime());
                boardList.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardList;
    }

    /** 글 한건 조회 */
    public Board selectOne(int bno){
        String sql = "select * from boardTable where bno = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bno);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Board board = new Board();
                    board.setBno(rs.getInt("bno"));
                    board.setBtitle(rs.getString("btitle"));
                    board.setBcontent(rs.getString("bcontent"));
                    board.setBwriter(rs.getString("bwriter"));
                    // Timestamp를 LocalDateTime으로 변환하여 타입 불일치 해결
                    board.setBdate(rs.getTimestamp("bdate").toLocalDateTime());
                    return board;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 글 수정 (제목, 내용만 수정)
    public boolean updateBoard(Board board) {
        String sql = "UPDATE boardTable SET btitle = ? , bcontent = ? WHERE bno = ? ";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setInt(3, board.getBno());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // 글 삭제
    public boolean deleteBoard(int bno) {
        String sql = "DELETE FROM boardTable WHERE bno = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bno);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}