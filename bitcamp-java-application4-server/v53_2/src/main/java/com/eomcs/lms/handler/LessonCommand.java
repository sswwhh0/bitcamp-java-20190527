package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

@Component
public class LessonCommand {

  private LessonDao lessonDao;

  public LessonCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @RequestMapping("/lesson/add")
  public void add(BufferedReader in, PrintStream out) {

    try {
      // 수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.
      Lesson lesson = new Lesson();

      // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
      lesson.setTitle(Input.getStringValue(in, out, "수업명? "));
      lesson.setContents(Input.getStringValue(in, out, "설명? "));
      lesson.setStartDate(Input.getDateValue(in, out, "시작일? "));
      lesson.setEndDate(Input.getDateValue(in, out, "종료일? "));
      lesson.setTotalHours(Input.getIntValue(in, out, "총수업시간? "));
      lesson.setDayHours(Input.getIntValue(in, out, "일수업시간? "));

      lessonDao.insert(lesson);
      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/lesson/delete")
  public void delete(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      if (lessonDao.delete(no) > 0) {
        // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
        out.println("데이터를 삭제하였습니다.");
      } else {
        out.println("해당 데이터가 없습니다.");
      }
    } catch(Exception e) {
      out.println("해당 번호의 데이터가 없습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/lesson/detail")
  public void detail(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      Lesson lesson = lessonDao.findBy(no);

      if (lessonDao == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      out.printf("수업명: %s\n", lesson.getTitle());
      out.printf("수업내용: %s\n", lesson.getContents());
      out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
      out.printf("총수업시간: %d\n", lesson.getTotalHours());
      out.printf("일수업시간: %d\n", lesson.getDayHours());
    } catch(Exception e) {
      out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/lesson/list")
  public void list(BufferedReader in, PrintStream out) {

    try {
      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        out.printf("%s, %s, %s ~ %s, %s\n", 
            lesson.getNo(), lesson.getTitle(), 
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
      }
    } catch(Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/lesson/update")
  public void update(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      Lesson data = new Lesson();
      data.setNo(no);

      String str = Input.getStringValue(in, out, "수업명(" + lesson.getTitle() + ")? ");
      if (str.length() > 0) {
        data.setTitle(str);
      }

      str = Input.getStringValue(in, out, "수업내용(" + lesson.getContents() + ")? ");
      if (str.length() > 0) {
        data.setContents(str);
      }

      data.setStartDate(
          Input.getDateValue(in, out, "시작일(" + lesson.getStartDate() + ")? "));

      data.setEndDate(
          Input.getDateValue(in, out, "종료일(" + lesson.getEndDate() + ")? "));

      try {
        data.setTotalHours(
            Input.getIntValue(in, out, "총수업시간(" + lesson.getTotalHours() + ")? "));
      } catch(Exception e) {
      }

      try {
        data.setDayHours(
            Input.getIntValue(in, out, "일수업시간(" + lesson.getDayHours() + ")? "));
      } catch(Exception e) {
      }

      lessonDao.update(data);
      out.println("데이터를 변경하였습니다.");

    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
  }

}












