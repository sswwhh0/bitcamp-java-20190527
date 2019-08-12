package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {
  private MemberDao memberDao;
  private Input input;
  
  public MemberUpdateCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    try {
      Member member = memberDao.findBy(no);
      if (member == null) {
        System.out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      
      String str = input.getStringValue("이름(" + member.getName() + ")? ");
      if (str.length() > 0) {
        member.setName(str);
      }
      
      str = input.getStringValue("이메일(" + member.getEmail() + ")? ");
      if (str.length() > 0) {
        member.setEmail(str);
      }
      
      str = input.getStringValue("암호? ");
      if (str.length() > 0) {
        member.setPassword(str);
      }
      
      str = input.getStringValue("사진(" + member.getPhoto() + ")? ");
      if (str.length() > 0) {
        member.setPhoto(str);
      }
      
      str = input.getStringValue("전화(" + member.getTel() + ")? ");
      if (str.length() > 0) {
        member.setTel(str);
      }

      memberDao.update(member);

      System.out.println("데이터를 변경하였습니다.");
    } catch(Exception e) {

    }
  }

}
