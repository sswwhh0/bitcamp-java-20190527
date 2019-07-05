//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    int no = getIntValue("번호 입력 : ");
    String content = getStringValue("내용 입력 : ");
    java.sql.Date writeDay = getDateValue("작성일 입력 : ");
    int inquiry = getIntValue("조회수 : ");
    
    System.out.println();
    System.out.println("번호 : "+no);
    System.out.println("내용 : "+content);
    System.out.println("작성일 : "+writeDay);
    System.out.println("조회수 : "+inquiry);
  }
  
  private static int getIntValue(String message) {
    while(true) {
      try {
        System.out.println(message);
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
  
  private static java.sql.Date getDateValue(String message) {
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