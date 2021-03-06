package com.eomcs.lms.servlet.BoardServlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private BoardDao boardDao;
  
  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    boardDao = appCtx.getBean(BoardDao.class);
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    
    // 다른 서블릿/jsp로 인클루딩 할 때는 이쪽에서 콘텐츠 타입을 설정해야 한다.
    response.setContentType("text/html;charset=UTF-8");
    try {
      List<Board> boards = boardDao.findAll();
      
      request.setAttribute("boards", boards);
      request.setAttribute("viewUrl", "/jsp/board/list.jsp");
      
    } catch (Exception e) {
      request.setAttribute("error", e);
    }
  }
}
