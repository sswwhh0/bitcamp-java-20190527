package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class CalcPlusCommand implements Command{
private Input input;
  
  public CalcPlusCommand(Input input) {
    this.input = input;
  }
  
  @Override
  public void execute() {
    
    while(true) {
      try {
        int value = input.getIntValue("값1? : ");
        int value2 = input.getIntValue("값2? : ");
        int plus = value+value2;
        System.out.println("합계 : "+plus);
        break;
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
    
  }
}
