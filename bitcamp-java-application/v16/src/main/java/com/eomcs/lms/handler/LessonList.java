package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

public class LessonList {
  private static int DEFAULT_CAPACITY = 100;
  private Lesson[] list;
  private int size = 0;
  
  public LessonList() {
    this(DEFAULT_CAPACITY);
  }
  
  public LessonList(int initialCapacity) {
    if(initialCapacity<50 || initialCapacity>10000)
      list = new Lesson[DEFAULT_CAPACITY];
    else
      list = new Lesson[initialCapacity];
  }
  
  public void add(Lesson lesson) {
    if(this.size == list.length) {
      throw new RuntimeException("배열이 꽉 찼습니다!");
    }
    else {
      this.list[this.size++] = lesson;
    }
  }
  
  public Lesson[] toArray() {
    Lesson[] arr = new Lesson[this.size];
    for(int i=0; i<this.size;i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }
  
}
