package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.ArrayList;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private ArrayList<Board> boardList = new ArrayList<>();
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
    board.setNum(input.getIntValue("번호 입력 : "));
    board.setTitle(input.getStringValue("제목 입력 : "));
    board.setContents(input.getStringValue("내용 입력 : "));
    board.setWriteDay(input.getDateValue("작성일 입력 : "));
  
    boardList.add(board);
    System.out.println("저장하였습니다.");
  }
  public void listBoard() {
    Board[] boards = new Board[boardList.size()];
    boardList.toArray(boards);
    for (Board board : boards) {
      System.out.printf("%s, %s, %s, %s\n", board.getNum(), board.getTitle(), 
          board.getContents(), board.getWriteDay());
    }
  }

  public void detailBoard() {
    int num = input.getIntValue("번호? ");
    
    //사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는
    //Lesson 객체를 찾는다
    Board board = null;
    
    for(int i=0; i<boardList.size(); i++) {
      Board temp = boardList.get(i);
      if(temp.getNum() == num) {
        board = temp;
        break;
      }
    }
    
    if(board == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }
    
    System.out.printf("제목 : %s\n", board.getTitle());
    System.out.printf("내용 : %s\n", board.getContents());
    System.out.printf("작성일 : %s\n", board.getWriteDay());
  }
  public void updateBoard() {
    int num = input.getIntValue("번호? ");
    
    //사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는
    //Lesson 객체를 찾는다
    Board board = null;
    
    for(int i=0; i<boardList.size(); i++) {
      Board temp = boardList.get(i);
      if(temp.getNum() == num) {
        board = temp;
        break;
      }
    }
    
    if(board == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }
    
    String str = input.getStringValue("제목("+board.getTitle()+")? ");
    if(str.length()>0) {
      board.setTitle(str);
    }
    
    str = input.getStringValue("내용("+board.getContents()+")? ");
    if(str.length()>0) {
      board.setContents(str);
    }
    
    board.setWriteDay(input.getDateValue("작성일("+board.getWriteDay()+")? "));
    
    System.out.println("변경 되었습니다!");
  }
  public void deleteBoard() {
    int num = input.getIntValue("번호? ");
    
    for(int i=0; i<boardList.size(); i++) {
      Board temp = boardList.get(i);
      if(temp.getNum() == num) {
        boardList.remove(i);
        System.out.println("삭제 되었습니다!");
        return;
      }
    }
    System.out.println("해당 번호의 데이터가 없습니다!");
  }
}
