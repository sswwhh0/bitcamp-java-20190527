package com.eomcs.lms.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

//애플리케이션이 시작되거나 종료될 때 보고를 받는 옵저버이다.
//옵저버의 역할을 수행하려면 옵저버 호출 규칙에 따라 작성해야 한다.
//즉 ApplicationContextListener를 구현해야 한다.
public class DataLoaderListener implements ApplicationContextListener {

  //Command 객체가 사용할 Collection 준비
  ArrayList<Lesson> lessonList = new ArrayList<>();
  LinkedList<Member> memberList = new LinkedList<>();
  ArrayList<Board> boardList = new ArrayList<>();

  @Override
  public void contextInitialized(Map<String, Object> beanContainer) {
    // 이전에 저장된 애플리케이션 데이터를 읽는다.
    loadLessonData();
    loadMemberData();
    loadBoardData();
    
    beanContainer.put("lessonList", lessonList);
    beanContainer.put("memberList", memberList);
    beanContainer.put("boardList", boardList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> beanContainer) {
    // 애플리케이션이 종료되기 전에 데이터를 저장한다.
    saveLessonData();
    saveMemberData();
    saveBoardData();
  }

  @SuppressWarnings("unchecked")
  private void loadLessonData() {

    File file = new File("./lesson.ser");

    // 바이트 단위로 출력된 데이터를 읽을 객체를 준비한다
    FileInputStream in = null;
    ObjectInputStream in2 = null;

    try {
      in = new FileInputStream(file);

      // 바이트 배열을 읽어 원래의 타입으로 변환해주는 도구를
      // FileInputStream에 붙인다.
      in2 = new ObjectInputStream(in);

      lessonList = (ArrayList<Lesson>) in2.readObject();

    } catch (FileNotFoundException e) {
      // 파일에 데이터를 출력하다가 오류가 발생하면,
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자
      System.out.println("Lesson 파일이 없습니다!");
    } catch (Exception e) {
      // FileNotFoundException외의 다른 예외를 여기에서 처리한다.
      System.out.println("Lesson 파일을 읽는 중에 오류가 발생했습니다");
    } finally {

      try {
        in2.close();
      } catch (Exception E) {
      }
      try {
        in.close();
      } catch (Exception E) {
      }
    }

  }

  private void saveLessonData() {

    // File의 정보를 준비
    File file = new File("./lesson.ser");

    // 바이트 단위로 데이터를 다루기 위해 바이트 스트림 클래스를 준비한다.
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      // 파일 정보를 바탕으로 데이터를 출력해주는 객체 준비
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);

      //lessonList를 통째로 출력하기
      out2.writeObject(lessonList);

    } catch (FileNotFoundException e) {
      // 파일에 데이터를 출력하다가 오류가 발생하면,
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자
      System.out.println("Lesson 파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("Lesson 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() {

    File file = new File("./member.ser");
    FileInputStream in = null;
    ObjectInputStream in2 = null;

    try {
      in = new FileInputStream(file);
      in2 = new ObjectInputStream(in);

      memberList = (LinkedList<Member>) in2.readObject();

    } catch (FileNotFoundException e) {
      // 파일에 데이터를 출력하다가 오류가 발생하면,
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자
      System.out.println("Member 파일이 없습니다!");
    } catch (Exception e) {
      // FileNotFoundException외의 다른 예외를 여기에서 처리한다.
      System.out.println("Member 파일을 읽는 중에 오류가 발생했습니다");
    } finally {
      try {
        in2.close();
      } catch (Exception E) {
      }
      try {
        in.close();
      } catch (Exception E) {
      }
    }

  }

  private void saveMemberData() {

    // File의 정보를 준비
    File file = new File("./member.ser");
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      // 파일 정보를 바탕으로 데이터를 출력해주는 객체 준비
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);

      out2.writeObject(memberList);

    } catch (FileNotFoundException e) {
      // 파일에 데이터를 출력하다가 오류가 발생하면,
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자
      System.out.println("Member 파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("Member 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {

    File file = new File("./board.ser");
    FileInputStream in = null;
    ObjectInputStream in2 = null;

    try {
      in = new FileInputStream(file);
      in2 = new ObjectInputStream(in);

      boardList = (ArrayList<Board>) in2.readObject();

    } catch (FileNotFoundException e) {
      // 파일에 데이터를 출력하다가 오류가 발생하면,
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자
      System.out.println("Board 파일이 없습니다!");
    } catch (Exception e) {
      // FileNotFoundException외의 다른 예외를 여기에서 처리한다.
      System.out.println("Board 파일을 읽는 중에 오류가 발생했습니다");
    } finally {

      try {
        in2.close();
      } catch (Exception E) {
      }
      try {
        in.close();
      } catch (Exception E) {
      }

    }

  }

  private void saveBoardData() {

    // File의 정보를 준비
    File file = new File("./board.ser");
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      // 파일 정보를 바탕으로 데이터를 출력해주는 객체 준비
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);

      out2.writeObject(boardList);

    } catch (FileNotFoundException e) {
      // 파일에 데이터를 출력하다가 오류가 발생하면,
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자
      System.out.println("Board 파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("Board 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

}
