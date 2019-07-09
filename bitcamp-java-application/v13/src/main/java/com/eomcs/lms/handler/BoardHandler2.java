package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler2 {
  private static Board[] boards2 = new Board[100];
  private static int boardSize2 = 0;
  public static Scanner keyScan;
  
  public static void addBoard2() {
    Board board2 = new Board();
    board2.num = Input.getIntValue("번호 입력 : ");
    board2.title = Input.getStringValue("제목 입력 : ");
    board2.contents = Input.getStringValue("내용 입력 : ");
    board2.writeDay = Input.getDateValue("작성일 입력 : ");
  
    boards2[boardSize2++] = board2;
    System.out.println("저장하였습니다.");
  }
  public static void listBoard2() {
    for(int i=0; i<boardSize2; i++) {
      System.out.printf("%s, %s, %s, %s", boards2[i].num, boards2[i].title, 
          boards2[i].contents, boards2[i].writeDay);
    }
  }
}
