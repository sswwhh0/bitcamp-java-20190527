//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    Board boards[] = new Board[100];
    
    int i=0;
    for(; i<boards.length; i++) {
      Board board = new Board();
      board.num = getIntValue("번호 입력 : ");
      board.title = getStringValue("제목 입력 : ");
      board.contents = getStringValue("내용 입력 : ");
      board.writeDay = getDateValue("작성일 입력 : ");
    
      boards[i] = board;
      System.out.println("계속 입력할래? (y/n)");
      String response = keyScan.nextLine();
    
      if(response.equals("n")) {
        break;
      }
    }
    
    for(int i2=0; i2<=i; i2++) {
      Board board = boards[i2];
      System.out.printf("%s, %s, %s, %s\n", board.num, board.title, 
          board.contents, board.writeDay);
    }
  }
  
  private static int getIntValue(String message) {
    while(true) {
      try {
        System.out.print(message);
        return Integer.valueOf(keyScan.nextLine());
      } catch(NumberFormatException e) {
        System.out.println("숫자를 입력하세요");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }
  
  private static Date getDateValue(String message) {
    while(true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch(IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요");
      }
    }
  }
  
}