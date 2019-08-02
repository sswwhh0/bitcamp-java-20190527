package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

// 게시물 요청을 처리하는 담당자
public class MemberServlet implements Servlet {
  
  ArrayList<Member> memberList = new ArrayList<>();
  
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }
  
  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/member/add":
        addMember();
        break;
      case "/member/list":
        listMember();
        break;
      case "/member/delete":
        deleteMember();
        break;  
      case "/member/detail":
        detailMember();
        break;
      case "/member/update":
        updateMember();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }
  
  private void updateMember() throws Exception {
    Member member = (Member)in.readObject();
    
    int index = indexOfMember(member.getNo());
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    memberList.set(index, member);
    out.writeUTF("ok");
  }

  private void detailMember() throws Exception {
    int no = in.readInt();
    
    int index = indexOfMember(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(memberList.get(index));
  }

  private void deleteMember() throws Exception {
    int no = in.readInt();
    
    int index = indexOfMember(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    memberList.remove(index);
    out.writeUTF("ok");
  }

  private void addMember() throws Exception {
    Member member = (Member)in.readObject();
    memberList.add(member);
    out.writeUTF("ok");
  }
  
  private void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberList);
  }
  
  private int indexOfMember(int no) {
    int i = 0;
    for (Member obj : memberList) {
      if (obj.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
