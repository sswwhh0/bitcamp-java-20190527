package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

@Component
public class BoardCommand {

  private BoardDao boardDao;

  public BoardCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @RequestMapping("/board/add")
  public void add(BufferedReader in, PrintStream out) {

    try {
      Board board = new Board();
      board.setContents(Input.getStringValue(in, out, "내용? "));

      boardDao.insert(board);
      out.println("저장하였습니다.");

    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/board/delete")
  public void delete(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");

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
  public void detail(BufferedReader in, PrintStream out) {

    try {
      //클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      Board board = boardDao.findBy(no);

      if (board == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      boardDao.increaseViewCount(no);

      out.printf("내용: %s\n", board.getContents());
      out.printf("작성일: %s\n", board.getCreatedDate());

    } catch(Exception e) {
      out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/board/list")
  public void list(BufferedReader in, PrintStream out) {
    //Board[] boards = new Board[boardList.size()];
    //boardList.toArray(boards);

    try {
      List<Board> boards = boardDao.findAll();
      for (Board board : boards) {
        out.printf("%s, %s, %s, %s\n", 
            board.getNo(), board.getContents(), 
            board.getCreatedDate(), board.getViewCount());
      }
    } catch(Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/board/update")
  public void update(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      Board board = boardDao.findBy(no);

      if (board == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      Board data = new Board();
      data.setNo(no);

      String str = Input.getStringValue(in, out, "내용? ");

      if (str.length() > 0) {
        data.setContents(str);
        boardDao.update(data);
        out.println("데이터를 변경하였습니다.");
      } else {
        out.println("데이터 변경을 취소합니다");
      }

    } catch(Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
