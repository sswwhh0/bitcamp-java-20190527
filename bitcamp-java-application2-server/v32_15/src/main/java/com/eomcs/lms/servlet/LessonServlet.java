package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.csv.LessonCsvDao;
import com.eomcs.lms.domain.Lesson;

// 게시물 요청을 처리하는 담당자
public class LessonServlet implements Servlet {
  
  LessonCsvDao lessonDao;
  
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public LessonServlet(LessonCsvDao lessonDao, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    this.lessonDao = lessonDao;
    
  }
  
  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/lesson/add":
        addLesson();
        break;
      case "/lesson/list":
        listLesson();
        break;
      case "/lesson/delete":
        deleteLesson();
        break;  
      case "/lesson/detail":
        detailLesson();
        break;
      case "/lesson/update":
        updateLesson();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }
  
  private void updateLesson() throws Exception {
    Lesson lesson = (Lesson)in.readObject();
    
    if(lessonDao.update(lesson) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void detailLesson() throws Exception {
    int no = in.readInt();
    
    Lesson lesson = lessonDao.findBy(no);
    if (lesson == null) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    System.out.println(no+"번을 조회했습니다");
    out.writeUTF("ok");
    out.writeObject(lesson);
  }

  private void deleteLesson() throws Exception {
    int no = in.readInt();
    
    if (lessonDao.delete(no) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    System.out.println(no+"번을 삭제했습니다");
    out.writeUTF("ok");
  }

  private void addLesson() throws Exception {
    Lesson lesson = (Lesson)in.readObject();
    
    if(lessonDao.insert(lesson) == 0) {
      fail("게시물을 입력할 수 없습니다.");
    }
    out.writeUTF("ok");
  }
  
  private void listLesson() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(lessonDao.findAll());
  }
  
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
