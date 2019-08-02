package com.eomcs.lms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Lesson;

// 게시물 요청을 처리하는 담당자
public class LessonServlet implements Servlet {
  
  ArrayList<Lesson> lessonList = new ArrayList<>();
  
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    this.in = in;
    this.out = out;
    
    try {
      loadData();
    } catch (IOException e) {
      System.out.println("Lesson게시물 데이터 로딩 중 오류 발생!");
    }
  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./lesson.ser");

    if(!file.exists()) {
      file.createNewFile();
    }

    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream(file))) {
      lessonList = (ArrayList<Lesson>) in.readObject();
      System.out.println("Lesson게시물 데이터 로딩 완료!");
    }
  }

  public void saveData() {
    File file = new File("./lesson.ser");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream(file));) {
      
      out.writeObject(lessonList);
      System.out.println("Lesson게시물 데이터 저장 완료!");
      
    } catch (FileNotFoundException e) {
      System.out.println("Lesson파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("Lesson파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    }
    
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
    
    int index = indexOfLesson(lesson.getNo());
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    lessonList.set(index, lesson);
    out.writeUTF("ok");
  }

  private void detailLesson() throws Exception {
    int no = in.readInt();
    
    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(lessonList.get(index));
  }

  private void deleteLesson() throws Exception {
    int no = in.readInt();
    
    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    lessonList.remove(index);
    System.out.println(no+"번을 삭제했습니다");
    out.writeUTF("ok");
  }

  private void addLesson() throws Exception {
    Lesson lesson = (Lesson)in.readObject();
    lessonList.add(lesson);
    out.writeUTF("ok");
  }
  
  private void listLesson() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(lessonList);
  }
  
  private int indexOfLesson(int no) {
    int i = 0;
    for (Lesson obj : lessonList) {
      if (obj.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
