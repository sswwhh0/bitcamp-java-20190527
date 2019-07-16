package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.ArrayList;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  private ArrayList<Member> memberList = new ArrayList<>();
  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {
    Member member = new Member();
    member.setNum(input.getIntValue("회원 번호 입력 : "));
    member.setName(input.getStringValue("이름 입력 : "));
    member.setPhoneNum(input.getStringValue("전화 입력 : "));
    member.setJoinDay(input.getDateValue("가입일 입력 : "));
    
    memberList.add(member);
    System.out.println("저장하였습니다.");
  }
  public void listMember() {
    Object[] list = memberList.toArray();
    for (Object obj : list) {
      Member member = (Member) obj;
      System.out.printf("%s, %s, %s, %s\n", member.getNum(), member.getName(), 
          member.getPhoneNum(), member.getJoinDay());
    }
  }

  public void detailMember() {
    int num = input.getIntValue("회원 번호? ");
    
    //사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는
    //Lesson 객체를 찾는다
    Member member = null;
    
    for(int i=0; i<memberList.size(); i++) {
      Member temp = memberList.get(i);
      if(temp.getNum() == num) {
        member = temp;
        break;
      }
    }
    
    if(member == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }
    
    System.out.printf("이름 : %s\n", member.getName());
    System.out.printf("전화 : %s\n", member.getPhoneNum());
    System.out.printf("가입일 : %s\n", member.getJoinDay());
  }
  public void updateMember() {
    int num = input.getIntValue("회원 번호? ");
    
    //사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는
    //Lesson 객체를 찾는다
    Member member = null;
    
    for(int i=0; i<memberList.size(); i++) {
      Member temp = memberList.get(i);
      if(temp.getNum() == num) {
        member = temp;
        break;
      }
    }
    
    if(member == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }
    
    String str = input.getStringValue("이름("+member.getName()+")? ");
    if(str.length()>0) {
      member.setName(str);
    }
    
    str = input.getStringValue("전화("+member.getPhoneNum()+")? ");
    if(str.length()>0) {
      member.setPhoneNum(str);
    }
    
    member.setJoinDay(input.getDateValue("가입일("+member.getJoinDay()+")? "));
    
    System.out.println("변경 되었습니다!");
  }
  public void deleteMember() {
    int num = input.getIntValue("회원 번호? ");
    
    for(int i=0; i<memberList.size(); i++) {
      Member temp = memberList.get(i);
      if(temp.getNum() == num) {
        memberList.remove(i);
        System.out.println("삭제 되었습니다!");
        return;
      }
    }
    System.out.println("해당 번호의 데이터가 없습니다!");
  }
}
