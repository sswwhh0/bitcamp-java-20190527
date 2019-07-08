package com.eomcs.lms;

import java.util.Scanner;

public class BoardHandler {
  static Board[] boards = new Board[100];
  static int boardSize = 0;
  static Scanner keyScan;
  
  static void addBoard() {
    Board board = new Board();
    board.num = Input.getIntValue("번호 입력 : ");
    board.title = Input.getStringValue("제목 입력 : ");
    board.contents = Input.getStringValue("내용 입력 : ");
    board.writeDay = Input.getDateValue("작성일 입력 : ");
  
    boards[boardSize++] = board;
    System.out.println("저장하였습니다.");
  }
  static void listBoard() {
    for(int i=0; i<boardSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.num, board.title, 
          board.contents, board.writeDay);
    }
  }
}
