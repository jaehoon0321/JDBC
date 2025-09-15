package jdbc_boards.view;

import jdbc_boards.Controller.BoardMenu;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        BoardMenu menu = new BoardMenu();
        try {menu.boardMenu();
        }
        catch (IOException | SQLException e)
        {System.err.println("애플리케이션 실행 중 오류가 발생했습니다: " + e.getMessage());}
    }
}
