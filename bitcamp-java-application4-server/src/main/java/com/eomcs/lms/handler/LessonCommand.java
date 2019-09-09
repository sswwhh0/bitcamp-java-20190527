package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@Component
public class LessonCommand {

  private LessonDao lessonDao;

  public LessonCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @RequestMapping("/lesson/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 등록폼</title></head>");
    out.println("<body><h1>게시물 등록폼</h1>");

    out.println("<form action='/lesson/add'>");
    out.println("수업명 : <textarea name='title' rows='2' cols='50'></textarea><br>");
    out.println("수업내용 : <textarea name='contents' rows='2' cols='50'></textarea><br>");
    out.println("시작일 : <textarea name='startDate' rows='2' cols='50'></textarea><br>");
    out.println("종료일 : <textarea name='endDate' rows='2' cols='50'></textarea><br>");
    out.println("총 수업시간 : <textarea name='totalHours' rows='2' cols='50'></textarea><br>");
    out.println("일 수업시간 : <textarea name='dayHours' rows='2' cols='50'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/add")
  public void add(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'></head>");
    out.println("<body><h1>게시물 등록</h1>");

    try {
      // 수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.
      Lesson lesson = new Lesson();

      // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));

      lessonDao.insert(lesson);
      out.println("<p>저장하였습니다.</p>");
    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/delete")
  public void delete(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'></head>");
    out.println("<body><h1>게시물 삭제</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      if (lessonDao.delete(no) > 0) {
        // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
        out.println("<p>데이터를 삭제하였습니다.</p>");
      } else {
        out.println("<p>해당 데이터가 없습니다.</p>");
      }
    } catch(Exception e) {
      out.println("<p>해당 번호의 데이터가 없습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/detail")
  public void detail(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 상세</title></head>");
    out.println("<body><h1>게시물 상세</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Lesson lesson = lessonDao.findBy(no);

      if (lessonDao == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
        return;
      }
      out.println("<form action='/lesson/update'>");
      out.printf("번호 : <input type="
          + "'text' name='no' value='%d' readonly><br>", lesson.getNo());
      out.printf("수업명: <textarea name='title' rows='1' "
          + "cols='50'>%s</textarea><br>\n", lesson.getTitle());
      out.printf("수업내용: <textarea name='contents' rows='3' "
          + "cols='50'>%s</textarea><br>\n", lesson.getContents());
      out.printf("시작일: <textarea name='startDate' rows='1' \"\r\n" + 
          "          + \"cols='100'>%s</textarea><br>\n", lesson.getStartDate());
      out.printf("종료일: <textarea name='endDate' rows='1' \"\r\n" + 
          "          + \"cols='100'>%s</textarea><br>\n",lesson.getEndDate());
      out.printf("총수업시간: <textarea name='totalhours' rows='1' \"\r\n" + 
          "          + \"cols='50'>%d</textarea><br>\n", lesson.getTotalHours());
      out.printf("일수업시간: <textarea name='dayhours' rows='1' \"\r\n" + 
          "          + \"cols='50'>%d</textarea><br>\n", lesson.getDayHours());
      out.println("<button>변경</button>");
      out.printf("<button><a href='/lesson/delete?no=%d'>삭제</a></button>\n",
          lesson.getNo());
      out.println("</form>");
    } catch(Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/list")
  public void list(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 목록</title></head>"
        + "<link rel=\'stylesheet\' \r\n" + 
        "href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' \r\n" + 
        "integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' \r\n" + 
        "crossorigin=\'anonymous\'>");
    out.println("<body><h1>게시물 목록</h1>");
    out.println("<a href='/lesson/form'>새 글</a><br>");

    try {

      out.println("<table class ='table table-hover'>");
      out.println("<tr><th>번호</th><th>수업명</th><th>수업내용</th><th>기간</th>"
          + "<th>총 수업시간</th><th>일 수업시간</th></tr>");

      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        out.printf("<tr><td>%d</td><td><a href='/lesson/detail?no=%d'>"
            + "%s</a></td><td>%s</td><td>%s ~ %s</td><td>%d</td><td>%d</td></tr>", 
            lesson.getNo(), lesson.getNo(), lesson.getTitle(), lesson.getContents(),  
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours(), lesson.getDayHours());
      }
    } catch(Exception e) {
      out.println("<p>데이터 목록 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/update")
  public void update(ServletRequest request, ServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>게시물 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'></head>");
    out.println("<body><h1>게시물 변경</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      Lesson data = new Lesson();
      data.setNo(Integer.parseInt(request.getParameter("no")));

      data.setTitle(request.getParameter("title"));

      data.setContents(request.getParameter("contents"));

      data.setStartDate(Date.valueOf(request.getParameter("startDate")));

      data.setEndDate(Date.valueOf(request.getParameter("endDate")));

      try {
        data.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
      } catch(Exception e) {
      }

      try {
        data.setDayHours(Integer.parseInt(request.getParameter("dayHours")));
      } catch(Exception e) {
      }

      lessonDao.update(data);
      out.println("<p>데이터를 변경하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 변경에 실패했습니다!</p>");
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");

  }

}












