package ch09.test;

public class Test {

  public static void main(String[] args) {
    
    //계산하기
    //-> 2*3-2+7 = ?
    //-> 6/2+4 = ?
    
    //"계산식2"의 결과를 저장할 메모리 생성
    Calculator calc1 = new Calculator();
    
    //"계산식2"의 결과를 저장할 메모리 생성
    Calculator calc2 = new Calculator();
    
    calc1.plus(2);
    calc2.plus(6);
    System.out.printf("결과식1 = %d\n", calc1.result);
    System.out.printf("결과식2 = %d\n", calc2.result);
    
    calc1.multiple(3);
    calc2.divide(2);
    System.out.printf("결과식1 = %d\n", calc1.result);
    System.out.printf("결과식2 = %d\n", calc2.result);
    
    calc1.minus(2);
    calc2.plus(4);
    System.out.printf("결과식1 = %d\n", calc1.result);
    System.out.printf("결과식2 = %d\n", calc2.result);
    
    calc1.plus(7);
    
    System.out.printf("결과식1 = %d\n", calc1.result);
    System.out.printf("결과식2 = %d\n", calc2.result);

  }

}
