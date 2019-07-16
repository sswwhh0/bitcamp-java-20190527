package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.util.ArrayList;
import com.eomcs.lms.util.Input;

public class LessonHandler {
  
  private ArrayList lessonList = new ArrayList();
  private Input input;
  
  public LessonHandler(Input input) {
    this.input = input;
  }
  
  public void addLesson() {
    //수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.
    Lesson lesson = new Lesson();
    //사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.setNo(input.getIntValue("번호 입력 : "));   
    lesson.setTitle(input.getStringValue("수업명 입력 : "));
    lesson.setContents(input.getStringValue("설명 입력 : "));
    lesson.setStartDate(input.getDateValue("시작일 입력 : "));
    lesson.setEndDate(input.getDateValue("종료일 입력 : "));
    lesson.setTotalHours(input.getIntValue("총 수업시간 입력 : "));
    lesson.setDayHours(input.getIntValue("일 수업시간 입력 : "));
    
    //수업데이터를 저장하고 있는 인스턴스의 주소를 레퍼런스 배열에 저장한다
    //LessonList에게 전달한다
    lessonList.add(lesson);
    System.out.println("저장하였습니다.");
  }
  public void listLesson() {
    Object[] lessons = lessonList.toArray();
    for (Object obj : lessons) {
      Lesson lesson = (Lesson) obj;
      //인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s ~ %s, %s, %s\n",lesson.getNo(), lesson.getTitle(), 
          lesson.getContents(), lesson.getStartDate(), 
          lesson.getEndDate(), lesson.getTotalHours(), lesson.getDayHours());
    }
  }
}
