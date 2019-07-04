//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    Scanner keyScan = new Scanner(keyboard);
    
    System.out.print("번호 입력 : ");
    String no = keyScan.nextLine();
    System.out.print("내용 입력 : ");
    String content = keyScan.nextLine();
    System.out.print("작성일 입력 : ");
    String writeDay = keyScan.nextLine();
    System.out.print("조회수 : ");
    String inquiry = keyScan.nextLine();
    
    System.out.println();
    System.out.println("번호 : "+no);
    System.out.println("내용 : "+content);
    System.out.println("작성일 : "+writeDay);
    System.out.println("조회수 : "+inquiry);
  }
}