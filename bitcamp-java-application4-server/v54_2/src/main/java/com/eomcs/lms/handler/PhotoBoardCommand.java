package com.eomcs.lms.handler;

import java.io.PrintWriter;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.ServletRequest;
import com.eomcs.util.ServletResponse;

@Component
public class PhotoBoardCommand {

  //  private static final Logger logger = 
  //      LogManager.getLogger(PhotoBoardCommand.class);

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @RequestMapping("/photoboard/form")
  public void form(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 등록폼</title></head>");
    out.println("<body><h1>게시물 등록폼</h1>");

    out.println("<form action='/photoboard/add'>");
    out.println("제목 : <textarea name='title' rows='5' cols='50'></textarea><br>");
    out.println("수업 번호 : <textarea name='lessonNo' rows='5' cols='50'></textarea><br>");
    out.println("사진 : <textarea name='filePath' rows='5' cols='50'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @Transactional
  @RequestMapping("/photoboard/add")
  public void add(ServletRequest request, ServletResponse response) {

    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoBoard/list'></head>");
    out.println("<body><h1>게시물 등록</h1>");

    try {
      PhotoBoard photoboard = new PhotoBoard();
      photoboard.setTitle(request.getParameter("title"));
      photoboard.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));
      photoBoardDao.insert(photoboard);

      PhotoFile photoFile = new PhotoFile();
      photoFile.setFilePath(request.getParameter("filePath"));
      photoFile.setBoardNo(photoboard.getNo());

      photoFileDao.insert(photoFile);

      out.println("<p>저장하였습니다.</p>");
    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      throw new RuntimeException(e);
    } finally {
      out.println("</body></html>");
    }
  }

  @Transactional
  @RequestMapping("/photoboard/delete")
  public void delete(ServletRequest request, ServletResponse response) {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'></head>");
    out.println("<body><h1>게시물 삭제</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if(photoBoardDao.findBy(no) == null) {
        out.println("<p>해당 데이터가 없습니다.</p>");
        return;
      }

      photoFileDao.deleteAll(no);
      photoBoardDao.delete(no);

      out.println("<p>데이터를 삭제하였습니다.</p>");
      out.flush();

    } catch(Exception e) {
      out.println("<p>데이터 삭제에 실패했습니다!</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/detail")
  public void detail(ServletRequest request, ServletResponse response) {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 상세</title></head>");
    out.println("<body><h1>게시물 상세</h1>");


    try {
      //클라이언트에게 번호를 요구하여 받는다.
      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if (photoBoard == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
        return;
      }

      photoBoardDao.increaseViewCount(no);
      out.println("<form action='/photoboard/update'>");
      out.printf("번호 : <input type="
          + "'text' name='no' value='%d' readonly><br>", photoBoard.getNo());
      out.printf("제목: <textarea name='title' rows='1' "
          + "cols='50'>%s</textarea><br>\n", photoBoard.getTitle());
      out.printf("작성일: %s<br>\n", photoBoard.getCreatedDate());
      out.printf("조회수: %s<br>\n", photoBoard.getViewCount());
      out.printf("수업 번호: %s<br>\n", photoBoard.getLessonNo());
      out.println("사진 파일 : ");

      List<PhotoFile> files = photoBoard.getFiles();
      for(PhotoFile file : files) {
        out.printf("> <textarea name='filePath' rows='1' "
            + "cols='50'>%s</textarea><br>\n", file.getFilePath());
      }

      out.println("<button>변경</button>");
      out.printf("<button><a href='/photoboard/delete?no=%d'>삭제</a></button>\n",
          photoBoard.getNo());
      out.println("</form>");

    } catch(Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/list")
  public void list(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    //Board[] boards = new Board[boardList.size()];
    //boardList.toArray(boards);

    out.println("<html><head><title>게시물 목록</title></head>"
        + "<link rel=\'stylesheet\' \r\n" + 
        "href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' \r\n" + 
        "integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' \r\n" + 
        "crossorigin=\'anonymous\'>");

    out.println("<body><h1>게시물 목록</h1>");
    out.println("<a href='/photoboard/form'>새 글</a><br>");

    try {

      out.println("<table class ='table table-hover'>");
      out.println("<tr><th>번호</th><th>제목</th><th>작성일</th>"
          + "<th>조회수</th><th>수업 번호</th></tr>");

      List<PhotoBoard> photoboards = photoBoardDao.findAll();
      for (PhotoBoard photoboard : photoboards) {
        out.printf("<tr><td>%d</td><td><a href='/photoboard/detail?no=%d'>" 
            + "%s</a></td><td>%s</td><td>%s</td><td>%s</td></tr>", 
            photoboard.getNo(), photoboard.getNo(), photoboard.getTitle(), 
            photoboard.getCreatedDate(), photoboard.getViewCount(), photoboard.getLessonNo());
      }
    } catch(Exception e) {
      out.println("<p>데이터 목록 조회에 실패했습니다!</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }

  @Transactional
  @RequestMapping("/photoboard/update")
  public void update(ServletRequest request, ServletResponse response) {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'></head>");
    out.println("<body><h1>게시물 변경</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoboard = photoBoardDao.findBy(no);

      if (photoboard == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      PhotoBoard data = new PhotoBoard();
      data.setNo(Integer.parseInt(request.getParameter("no")));
      data.setTitle(request.getParameter("title"));
      
      photoBoardDao.update(data);

      out.println("사진 파일 : ");
      List<PhotoFile> files = photoFileDao.findAll(no);
      for(PhotoFile file : files) {
        out.printf("> %s\n", file.getFilePath());
      }
      // 기존 사진 파일을 삭제한다.
      photoFileDao.deleteAll(no);

      String filepath = request.getParameter("filePath");

      PhotoFile photoFile = new PhotoFile();
      photoFile.setFilePath(filepath);
      photoFile.setBoardNo(photoboard.getNo());

      photoFileDao.insert(photoFile);
      out.println("<p>데이터를 변경하였습니다</p>");

    } catch(Exception e) {
      try {
      } catch(Exception e2) {
      }
      out.println("<p>데이터 변경에 실패했습니다!</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }
  
}
