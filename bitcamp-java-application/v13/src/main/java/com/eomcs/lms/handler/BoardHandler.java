package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private Board[] boards = new Board[100];
  private int boardSize = 0;
  public static Scanner keyScan;
  
  public void addBoard() {
    Board board = new Board();
    board.num = Input.getIntValue("번호 입력 : ");
    board.title = Input.getStringValue("제목 입력 : ");
    board.contents = Input.getStringValue("내용 입력 : ");
    board.writeDay = Input.getDateValue("작성일 입력 : ");
  
    boards[boardSize++] = board;
    System.out.println("저장하였습니다.");
  }
  public void listBoard() {
    for(int i=0; i<boardSize; i++) {
//      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", boards[i].num, boards[i].title, 
          boards[i].contents, boards[i].writeDay);
    }
  }
}
