//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    int[] no = new int[100];
    String[] lectureName = new String[100];
    String[] description = new String[100];
    Date[] startDate = new Date[100];
    Date[] endDate = new Date[100];
    int[] totalHours = new int[100];
    int[] dayHours = new int[100];
    
    int i=0;
        
    for(; i<no.length; i++) {
      no[i] = getIntValue("번호 입력 : ");   
      lectureName[i] = getStringValue("수업명 입력 : ");
      description[i] = getStringValue("설명 입력 : ");
      startDate[i] = getDateValue("시작일 입력 : ");
      endDate[i] = getDateValue("종료일 입력 : ");
      totalHours[i] = getIntValue("총 수업시간 입력 : ");
      dayHours[i] = getIntValue("일 수업시간 입력 : ");
      
      System.out.println("계속 입력하시겠습니까? (y/n)");
      String response = keyScan.nextLine();
      
      if(response.equals("n")) {
        break;
      }
    }
    
    for (int i2=0; i2<=i; i2++) {
      System.out.printf("%s, %s, %s, %s ~ %s, %s, %s\n",no[i2], lectureName[i2], 
          description[i2], startDate[i2], endDate[i2], totalHours[i2], dayHours[i2]);
    }
  }
  
  private static int getIntValue(String message) {
    while(true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch(NumberFormatException e) {
        System.out.println("숫자를 입력하세요");
      }
    }
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
  
  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }
  
}
