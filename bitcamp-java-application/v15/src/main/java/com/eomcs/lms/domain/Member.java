package com.eomcs.lms.domain;

import java.sql.Date;

public class Member {

  private int num;
  private String name;
  private String phoneNum;
  private Date joinDay;
  
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
  public Date getJoinDay() {
    return joinDay;
  }
  public void setJoinDay(Date joinDay) {
    this.joinDay = joinDay;
  }
  
}
