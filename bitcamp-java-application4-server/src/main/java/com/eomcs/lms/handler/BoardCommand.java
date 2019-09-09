package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component
public class BoardCommand {

  private BoardDao boardDao;

  public BoardCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @RequestMapping("/board/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 등록폼</title></head>");
    out.println("<body><h1>게시물 등록폼</h1>");

    out.println("<form action='/board/add'>");
    out.println("내용 : <textarea name='contents' rows='5' cols='50'></textarea>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @RequestMapping("/board/add")
  public void add(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/board/list'></head>");
    out.println("<body><h1>게시물 등록</h1>");

    try {
      Board board = new Board();
      board.setContents(request.getParameter("contents"));

      boardDao.insert(board);
      out.println("<p>저장하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/board/delete")
  public void delete(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();
    
    out.println("<html><head><title>게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/board/list'></head>");
    out.println("<body><h1>게시물 삭제</h1>");
    
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if (boardDao.delete(no) > 0) {
        out.println("데이터를 삭제하였습니다.");
      } else {
        out.println("해당 데이터가 없습니다");
      }
    } catch(Exception e) {
      out.println("해당 번호의 데이터가 없습니다!");
    }
  }

  @RequestMapping("/board/detail")
  public void detail(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 상세</title></head>");
    out.println("<body><h1>게시물 상세</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findBy(no);

      if (board == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
        return;
      } else {
        out.println("<form action='/board/update'>");
        out.printf("번호 : <input type="
            + "'text' name='no' value='%d' readonly><br>", board.getNo());
        out.printf("내용 : <textarea name='contents' rows='5' "
            + "cols='50'>%s</textarea><br>\n", board.getContents());
        out.printf("등록일 : %s<br>\n", board.getCreatedDate());
        out.printf("조회수 : %s<br>\n", board.getViewCount());
        out.println("<button>변경</button>");
        out.printf("<button><a href='/board/delete?no=%d'>삭제</a></button>\n",
            board.getNo());
        out.println("</form>");
        boardDao.increaseViewCount(no);
      }
    } catch(Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/board/list")
  public void list(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    //Board[] boards = new Board[boardList.size()];
    //boardList.toArray(boards);

    out.println("<html><head><title>게시물 목록</title></head>"
        + "<link rel=\'stylesheet\' \r\n" + 
        "href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' \r\n" + 
        "integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' \r\n" + 
        "crossorigin=\'anonymous\'>");
    out.println("<body><h1>게시물 목록</h1>");
    out.println("<a href='/board/form'>새 글</a><br>");

    try {
      out.println("<table class ='table table-hover'>");
      out.println("<tr><th>번호</th><th>내용</th><th>등록일</th><th>조회수</th></tr>");

      List<Board> boards = boardDao.findAll();
      for (Board board : boards) {
        out.printf("<tr><td>%d</td><td><a href='/board/detail?no=%d'>"
            + "%s</a></td><td>%s</td><td>%s</td></tr>", 
            board.getNo(), board.getNo(), board.getContents(), 
            board.getCreatedDate(), board.getViewCount());
      }
    } catch(Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/board/update")
  public void update(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();
    
    out.println("<html><head><title>게시물 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/board/list'></head>");
    out.println("<body><h1>게시물 변경</h1>");

    try {
      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setContents(request.getParameter("contents"));

      boardDao.update(board);
      out.println("<p>변경 했다</p>");
      
    } catch(Exception e) {
      out.println("<p>데이터 변경에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

}
