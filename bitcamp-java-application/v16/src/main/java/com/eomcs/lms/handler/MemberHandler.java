package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  private MemberList memberList = new MemberList();
  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {
    Member member = new Member();
    member.setNum(input.getIntValue("회원번호 입력 : "));
    member.setName(input.getStringValue("이름 입력 : "));
    member.setPhoneNum(input.getStringValue("전화 입력 : "));
    member.setJoinDay(input.getDateValue("가입일 입력 : "));
    
    memberList.add(member);
    System.out.println("저장하였습니다.");
  }
  public void listMember() {
    Member[] members = memberList.toArray();
    for (Member member : members) {
      System.out.printf("%s, %s, %s, %s\n", member.getNum(), member.getName(), 
          member.getPhoneNum(), member.getJoinDay());
    }
  }
}
