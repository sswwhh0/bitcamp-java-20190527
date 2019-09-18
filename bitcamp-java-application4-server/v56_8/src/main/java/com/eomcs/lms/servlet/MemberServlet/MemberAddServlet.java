package com.eomcs.lms.servlet.MemberServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@MultipartConfig(maxFileSize = 1024 * 0124 * 10)
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  String uploadDir;
  private MemberDao memberDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  
    uploadDir = getServletContext().getRealPath("/upload/member");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 등록폼</title></head>");
    out.println("<body><h1>회원 등록폼</h1>");
    out.println("<form action='/member/add' method='post' "
        + "enctype='multipart/form-data'>");
    out.println("이름: <input type='text' name='name'><br>");
    out.println("이메일: <input type='text' name='email'><br>");
    out.println("암호: <input type='text' name='password'><br>");
    out.println("사진: <input type='file' name='photo'><br>");
    out.println("전화: <input type='text' name='tel'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    try {
      Member member = new Member();
      
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setTel(request.getParameter("tel"));

      Part photoPart = request.getPart("photo");
      if(photoPart != null && photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        member.setPhoto(filename);
        photoPart.write(uploadDir + "/" + filename);
      }
      
      memberDao.insert(member);
      response.sendRedirect("/member/list");
      
    } catch (Exception e) {
      request.setAttribute("message", "데이터 저장에 실패했습니다!");
      request.setAttribute("refresh", "/member/list");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/error").forward(request,  response);
    }
  }
}
