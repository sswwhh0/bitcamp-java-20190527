package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  private static Member[] members = new Member[100];
  private static int memberSize = 0;
  public static Scanner keyScan;
  
  public static void addMember() {
    Member member = new Member();
    member.num = Input.getIntValue("회원번호 입력 : ");
    member.name = Input.getStringValue("이름 입력 : ");
    member.phoneNum = Input.getStringValue("전화 입력 : ");
    member.joinDay = Input.getDateValue("가입일 입력 : ");
    
    members[memberSize++] = member;
    System.out.println("저장하였습니다.");
  }
  public static void listMember() {
    for(int i=0; i<memberSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s\n", member.num, member.name, 
          member.phoneNum, member.joinDay);
    }
  }
}
