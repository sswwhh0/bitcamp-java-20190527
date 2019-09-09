package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component
public class LoginCommand {

  private MemberDao memberDao;
  Scanner scan;

  public LoginCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @RequestMapping("/auth/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>로그인 폼</title></head>");
    out.println("<body><h1>로그인 폼</h1>");
    
    out.println("<form action='/auth/login'>");
    out.println("이메일: <input type='text' name='email'><br>");
    out.println("암호: <input type='text' name='password'><br>");
    out.println("<button>로그인</button>&nbsp;&nbsp;"
        + "<button><a href='/member/form'>회원가입</a></button>\n");
    out.println("</form>");
    
    out.println("</body></html>");
    
    
  }

  @RequestMapping("/auth/login")
  public void execute(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>로그인 결과</title></head>");
    out.println("<body><h1>로그인</h1>");

    try {
      HashMap<String,Object> params = new HashMap<>();
      
      params.put("email", request.getParameter("email"));
      params.put("password", request.getParameter("password"));
      
      Member member = memberDao.findByEmailPassword(params);
      
      if(member == null) {
        out.println("<p>이메일 또는 암호가 맞지 않습니다!</p>");
      } else {
        out.printf("<p>%s님 환영합니다.\n</p>", member.getName());
      }
    } catch (Exception e) {
      out.println("<p>로그인 실행에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
  }

}
