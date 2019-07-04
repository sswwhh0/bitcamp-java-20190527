//애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    Scanner keyScan = new Scanner(keyboard);
    
    System.out.print("번호 입력 : ");
    String no = keyScan.nextLine();
    System.out.print("이름 입력 : ");
    String name = keyScan.nextLine();
    System.out.print("이메일 입력 : ");
    String email = keyScan.nextLine();
    System.out.print("암호 입력 : ");
    String pw = keyScan.nextLine();
    System.out.print("사진 입력 : ");
    String photo = keyScan.nextLine();
    System.out.print("전화 입력 : ");
    String phoneNum = keyScan.nextLine();
    System.out.print("가입일 입력 : ");
    String joinDay = keyScan.nextLine();
    
    System.out.println();
    System.out.println("번호 : "+no);
    System.out.println("이름 : "+name);
    System.out.println("이메일 : "+email);
    System.out.println("암호 : "+pw);
    System.out.println("사진 : "+photo);
    System.out.println("전화 : "+phoneNum);
    System.out.println("가입일 : "+joinDay);
  }
}