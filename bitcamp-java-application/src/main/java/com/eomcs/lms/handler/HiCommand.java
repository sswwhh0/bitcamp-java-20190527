package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class HiCommand implements Command{
  
  private Input input;
  
  public HiCommand(Input input) {
    this.input = input;
  }
  
  @Override
  public void execute() {
    
    String name = input.getStringValue("이름? : ");
    System.out.println(name+"님 안녕하세요");
    
  }
}
