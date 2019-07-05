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
    
    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    String[] pw = new String[100];
    String[] photo = new String[100];
    int[] phoneNum = new int[100];
    Date[] joinDay = new Date[100];
    
    int i=0;
    for(; i<no.length; i++) {
      no[i] = getIntValue("번호 입력 : ");
      name[i] = getStringValue("이름 입력 : ");
      email[i] = getStringValue("이메일 입력 : ");
      pw[i] = getStringValue("암호 입력 : ");
      photo[i] = getStringValue("사진 입력 : ");
      phoneNum[i] = getIntValue("전화 입력 : ");
      joinDay[i] = getDateValue("가입일 입력 : ");
      
      System.out.println("계속 입력할래? (y/n)");
      String response = keyScan.nextLine();
      
      if(response.equals("n")) {
        break;
      }
    }
      
    for(int i2=0; i2<=i; i2++) {
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",no[i2], name[i2], email[i2], 
          pw[i2], photo[i2], phoneNum[i2], joinDay[i2]);
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