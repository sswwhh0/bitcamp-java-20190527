// 애플리케이션 메인 클래스
// -> 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static Scanner keyScan;
  static Lesson[] lessons = new Lesson[100];
  static Member[] members = new Member[100];
  static Board[] boards = new Board[100];
  
  static int lessonSize = 0;
  static int memberSize = 0;
  static int boardSize = 0;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    while(true) {
      String command = prompt();
      
      if(command.equals("quit")) {
        System.out.println("종료되었습니다");
        break;
      }else if(command.equals("/lesson/add")) {
        addLesson();
      }else if(command.equals("/lesson/list")) {
        listLesson();
      }else if(command.equals("/member/add")) {
        addMember();
      }else if(command.equals("/member/list")) {
        listMember();
      }else if(command.equals("/board/add")) {
        addBoard();
      }else if(command.equals("/board/list")) {
        listBoard();
      }else {
        System.out.println("해당 명령을 지원하지 않습니다.");
      }
      System.out.println();
    }
  }
  static String prompt() {
    System.out.print("명령 > ");
    return keyScan.nextLine();
  }
  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요");
      }
    }
  }
  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요");
      }
    }
  }
  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }
  static void addLesson() {
    //수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.
    Lesson lesson = new Lesson();
    //사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.no = getIntValue("번호 입력 : ");   
    lesson.title = getStringValue("수업명 입력 : ");
    lesson.contents = getStringValue("설명 입력 : ");
    lesson.startDate = getDateValue("시작일 입력 : ");
    lesson.endDate = getDateValue("종료일 입력 : ");
    lesson.totalHours = getIntValue("총 수업시간 입력 : ");
    lesson.dayHours = getIntValue("일 수업시간 입력 : ");
    //수업데이터를 저장하고 있는 인스턴스의 주소를 레퍼런스 배열에 저장한다
    lessons[lessonSize++] = lesson;
    System.out.println("저장하였습니다.");
  }
  static void listLesson() {
    for (int i=0; i<lessonSize; i++) {
      //레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다
      Lesson lesson = lessons[i];
      //인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s ~ %s, %s, %s\n",lesson.no, lesson.title, 
          lesson.contents, lesson.startDate, lesson.endDate, lesson.totalHours, lesson.dayHours);
    }
  }
  static void addMember() {
    Member member = new Member();
    member.num = getIntValue("회원번호 입력 : ");
    member.name = getStringValue("이름 입력 : ");
    member.phoneNum = getStringValue("전화 입력 : ");
    member.joinDay = getDateValue("가입일 입력 : ");
    
    members[memberSize++] = member;
    System.out.println("저장하였습니다.");
  }
  static void listMember() {
    for(int i=0; i<memberSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s\n", member.num, member.name, 
          member.phoneNum, member.joinDay);
    }
  }
  static void addBoard() {
    Board board = new Board();
    board.num = getIntValue("번호 입력 : ");
    board.title = getStringValue("제목 입력 : ");
    board.contents = getStringValue("내용 입력 : ");
    board.writeDay = getDateValue("작성일 입력 : ");
  
    boards[boardSize++] = board;
    System.out.println("저장하였습니다.");
  }
  static void listBoard() {
    for(int i=0; i<boardSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.num, board.title, 
          board.contents, board.writeDay);
    }
  }
}
