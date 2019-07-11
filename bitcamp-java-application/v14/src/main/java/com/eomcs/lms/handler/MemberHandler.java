package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  private Member[] members = new Member[100];
  private int memberSize = 0;
  
  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {
    Member member = new Member();
    member.num = input.getIntValue("회원번호 입력 : ");
    member.name = input.getStringValue("이름 입력 : ");
    member.phoneNum = input.getStringValue("전화 입력 : ");
    member.joinDay = input.getDateValue("가입일 입력 : ");
    
    members[memberSize++] = member;
    System.out.println("저장하였습니다.");
  }
  public void listMember() {
    for(int i=0; i<memberSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s\n", member.num, member.name, 
          member.phoneNum, member.joinDay);
    }
  }
}
