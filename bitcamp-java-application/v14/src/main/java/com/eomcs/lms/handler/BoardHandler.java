package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private Board[] boards = new Board[100];
  private int boardSize = 0;
  
  private Input input;
  
  //BoardHandler가 사용하는 Input객체를 반드시 설정하도록 강제해보자!
  //-> Input객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  //   "의존 객체(dependency)"라 부른다.
  //-> 의존 객체를 강제로 설정하게 만드는 방법?
  //   다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.
  public BoardHandler(Input input) {
    this.input = input;
  }
  
  public void addBoard() {
    Board board = new Board();
    board.num = input.getIntValue("번호 입력 : ");
    board.title = input.getStringValue("제목 입력 : ");
    board.contents = input.getStringValue("내용 입력 : ");
    board.writeDay = input.getDateValue("작성일 입력 : ");
  
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
