package jdbc_boards.vo;

import java.time.LocalDateTime;

public class Board {

    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private LocalDateTime bdate;

    public Board() {
    }

    public Board(String btitle, String bcontent, String bwriter) {
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    }

    public Board(int bno, String btitle, String bcontent, String bwriter, LocalDateTime bdate) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
        this.bdate = bdate;
    }

    // Getter와 Setter 메소드
    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    public LocalDateTime getBdate() {
        return bdate;
    }

    public void setBdate(LocalDateTime bdate) {
        this.bdate = bdate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno + ", btitle='" + btitle + '\'' + ", bcontent='" + bcontent + '\'' + ", bwriter='" + bwriter + '\'' + ", bdate=" + bdate +
                '}';
    }
}