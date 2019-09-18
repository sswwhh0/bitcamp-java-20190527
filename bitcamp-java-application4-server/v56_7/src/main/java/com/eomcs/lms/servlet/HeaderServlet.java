package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.lms.domain.Member;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    
    out.println("<div id='header'>");
    out.println("<img src='/images/index.png'>");
    out.println("<span>수업 관리 시스템</span>");
    
    HttpSession session = request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    

    if(loginUser == null) {
      out.println("<a href='/auth/login'>로그인</a>");
    } else {
      out.println("<a href='/auth/logout'>로그아웃</a>");
      out.printf("<a href='/member/detail?no=%d'>%s</a>", 
          loginUser.getNo(), loginUser.getName());
    }
    
    out.println("</div>");
  }
}
