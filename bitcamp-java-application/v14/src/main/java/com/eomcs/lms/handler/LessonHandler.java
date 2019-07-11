package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.util.Input;

public class LessonHandler {
  private Lesson[] lessons = new Lesson[100];
  private int lessonSize = 0;
  
  private Input input;
  
  public LessonHandler(Input input) {
    this.input = input;
  }
  
  public void addLesson() {
    //수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.
    Lesson lesson = new Lesson();
    //사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.no = input.getIntValue("번호 입력 : ");   
    lesson.title = input.getStringValue("수업명 입력 : ");
    lesson.contents = input.getStringValue("설명 입력 : ");
    lesson.startDate = input.getDateValue("시작일 입력 : ");
    lesson.endDate = input.getDateValue("종료일 입력 : ");
    lesson.totalHours = input.getIntValue("총 수업시간 입력 : ");
    lesson.dayHours = input.getIntValue("일 수업시간 입력 : ");
    //수업데이터를 저장하고 있는 인스턴스의 주소를 레퍼런스 배열에 저장한다
    lessons[lessonSize++] = lesson;
    System.out.println("저장하였습니다.");
  }
  public void listLesson() {
    for (int i=0; i<lessonSize; i++) {
      //레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다
      Lesson lesson = lessons[i];
      //인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s ~ %s, %s, %s\n",lesson.no, lesson.title, 
          lesson.contents, lesson.startDate, lesson.endDate, lesson.totalHours, lesson.dayHours);
    }
  }
}
