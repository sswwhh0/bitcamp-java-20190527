package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command{
  
  private List<Lesson> lessonList;
  
  public LessonListCommand(Input input, List<Lesson> list) {
    this.lessonList = list;
  }

  public void execute() {
    Lesson[] lessons = lessonList.toArray(new Lesson[] {});
    for (Lesson lesson : lessons) {
      System.out.printf("%s, %s, %s ~ %s, %s\n", 
          lesson.getNo(), lesson.getTitle(), 
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
    }
  }

}












