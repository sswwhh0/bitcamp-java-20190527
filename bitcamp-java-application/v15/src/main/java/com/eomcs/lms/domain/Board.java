package com.eomcs.lms.domain;

import java.sql.Date;

public class Board {
  
  private int num;
  private String title;
  private String contents;
  private Date writeDay;
  
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public Date getWriteDay() {
    return writeDay;
  }
  public void setWriteDay(Date writeDay) {
    this.writeDay = writeDay;
  }

}
