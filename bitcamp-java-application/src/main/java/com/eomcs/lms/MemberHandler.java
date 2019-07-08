package com.eomcs.lms;

import java.util.Scanner;

public class MemberHandler {
  static Member[] members = new Member[100];
  static int memberSize = 0;
  static Scanner keyScan;
  
  static void addMember() {
    Member member = new Member();
    member.num = Input.getIntValue("회원번호 입력 : ");
    member.name = Input.getStringValue("이름 입력 : ");
    member.phoneNum = Input.getStringValue("전화 입력 : ");
    member.joinDay = Input.getDateValue("가입일 입력 : ");
    
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
}
