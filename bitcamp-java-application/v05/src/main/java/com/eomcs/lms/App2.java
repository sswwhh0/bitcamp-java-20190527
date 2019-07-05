//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    int no = getIntValue("번호 입력 : ");
    String name = getStringValue("이름 입력 : ");
    String email = getStringValue("이메일 입력 : ");
    String pw = getStringValue("암호 입력 : ");
    String photo = getStringValue("사진 입력 : ");
    int phoneNum = getIntValue("전화 입력 : ");
    java.sql.Date joinDay = getDateValue("가입일 입력 : ");
    
    System.out.println();
    System.out.println("번호 : "+no);
    System.out.println("이름 : "+name);
    System.out.println("이메일 : "+email);
    System.out.println("암호 : "+pw);
    System.out.println("사진 : "+photo);
    System.out.println("전화 : "+phoneNum);
    System.out.println("가입일 : "+joinDay);
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