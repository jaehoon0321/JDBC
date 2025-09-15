package jdbc_boards.Controller;

import jdbc_boards.model.BoardDao;
import util.DBUtil;
import jdbc_boards.vo.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardMenu {


    private BoardDao dao;
    private BufferedReader input;


    public BoardMenu() {
        this.dao = new BoardDao();
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    public void boardMenu() throws IOException, SQLException {
        System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
        System.out.println("메뉴 선택:");
        int choice = 0;
        try {
            String line = input.readLine();
            // 입력이 없을 경우 NumberFormatException이 발생하지 않도록 처리
            if (line == null || line.trim().isEmpty()) {
                return;
            }
            choice = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println("입력도중 에러 발생");
        } catch (NumberFormatException e1) {
            System.out.println("숫자만 입력하라니까");
        } catch (Exception e2) {
            System.out.println("꿰엑 에라 모르겠다.");
        }

        switch (choice) {
            case 1:
                //사용자에게 title,content를 입력받아서 Board 구성하여 createBoard()넘겨주자
                Board row = boardDataInput();

                // createBoard()를 호출하기 전에 작성자 정보를 추가로 받습니다.
                System.out.print("작성자 입력: ");
                String writer = input.readLine();
                row.setBwriter(writer);

                boolean ack = dao.createBoard(row);
                if (ack) {
                    System.out.println("글이 성공적으로 입력되었습니다.");
                } else {
                    System.out.println("입력 실패, 다시 시도 부탁드립니다. ");
                    //원하는 위치로 이동
                }
                break;
        }
    }

    public Board boardDataInput() throws IOException {
        Board board = new Board();
        System.out.println("새로운 글 입력");
        System.out.println("제목 입력");
        String title = input.readLine();
        board.setBtitle(title);
        System.out.println("내용 입력");
        String content = input.readLine();
        board.setBcontent(content);
        return board;
    }

    public boolean createBoard(Board board) {
        // SQL 쿼리에서 'board table'을 'boardTable'로 수정
        String sql = "INSERT INTO BoardTable(btitle, bcontent, bwriter, bdate) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // SQL 쿼리를 매개변수로 전달

            // 데이터 바인딩
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setString(3, board.getBwriter());

            // 쿼리 실행 및 결과 반환
            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            // SQL 관련 오류가 발생하면 오류 메시지 출력
            System.err.println("데이터베이스 오류 발생: " + e.getMessage());
            return false;
        }
    }

}