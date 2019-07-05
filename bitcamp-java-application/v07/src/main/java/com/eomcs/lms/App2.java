//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    Member[] members = new Member[100];
    
    int i=0;
    for(; i<members.length; i++) {
      Member member = new Member();
      member.num = getIntValue("회원번호 입력 : ");
      member.name = getStringValue("이름 입력 : ");
      member.phoneNum = getStringValue("전화 입력 : ");
      member.joinDay = getDateValue("가입일 입력 : ");
      
      members[i] = member;
      System.out.println("계속 입력할래? (y/n)");
      String response = keyScan.nextLine();
      
      if(response.equals("n")) {
        break;
      }
    }
      
    for(int i2=0; i2<=i; i2++) {
      Member member = members[i2];
      System.out.printf("%s, %s, %s, %s\n", member.num, member.name, 
          member.phoneNum, member.joinDay);
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