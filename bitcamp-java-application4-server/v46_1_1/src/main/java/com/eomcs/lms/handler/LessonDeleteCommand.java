package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.util.Input;

public class LessonDeleteCommand implements Command {

  private LessonDao lessonDao;

  public LessonDeleteCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  public String getCommandName() {
    return "/lesson/detail";
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

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
}












