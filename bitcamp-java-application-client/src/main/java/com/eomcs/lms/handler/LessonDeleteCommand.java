package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.util.Input;

public class LessonDeleteCommand implements Command {

  private LessonDao lessonDao;
  private Input input;

  public LessonDeleteCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    try {
      lessonDao.delete(no);
      // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
      System.out.println("데이터를 삭제하였습니다.");
      return;
    } catch(Exception e) {
      System.out.println("해당 번호의 데이터가 없습니다!");
    }

  }
}












