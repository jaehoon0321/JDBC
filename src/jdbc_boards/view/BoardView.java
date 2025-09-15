package jdbc_boards.view;

import jdbc_boards.vo.Board;
import java.util.List;

public class BoardView {

    public void displayMenu() {
        System.out.println("============== 게시판 메뉴 ==============");
        System.out.println("1. 새 글 작성");
        System.out.println("2. 전체 글 목록 보기");
        System.out.println("3. 글 상세 보기");
        System.out.println("4. 글 수정");
        System.out.println("5. 글 삭제");
        System.out.println("6. 종료");
        System.out.println("=========================================");
    }

    public void displayBoardList(List<Board> boardList) {
        System.out.println("-------------- 게시글 목록 --------------");
        System.out.printf("%-5s%-20s%-10s%-20s", "번호", "제목", "작성자", "작성일");
        System.out.println("----------------------------------------");
        if (boardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (Board board : boardList) {
                System.out.printf("%-5d%-20s%-10s%-20s",
                        board.getBno(),
                        board.getBtitle(),
                        board.getBwriter(),
                        board.getBdate());
            }
        }
        System.out.println("----------------------------------------");
    }

    public void displayBoardDetails(Board board) {
        System.out.println("-------------- 글 상세 보기 --------------");
        System.out.println("번호: " + board.getBno());
        System.out.println("제목: " + board.getBtitle());
        System.out.println("작성자: " + board.getBwriter());
        System.out.println("작성일: " + board.getBdate());
        System.out.println("내용: " + board.getBcontent());
        System.out.println("------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}