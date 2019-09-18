package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    out.println("<a href='/auth/logout'>로그아웃</a>");
    out.println("<a href='/auth/login'>로그인</a>");
    out.println("</div>");
  }
}
