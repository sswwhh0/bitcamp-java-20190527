package ch06;

public class TestX {
    public static void main(String[] args) {
  
      String name = System.getProperty("name");
      int kor = Integer.parseInt(System.getProperty("kor"));
      int eng = Integer.parseInt(System.getProperty("eng"));
      int math = Integer.parseInt(System.getProperty("math")); 
      
      int sum = kor+eng+math;
      System.out.printf("이름 : %s\n", name);
      System.out.printf("총점 : %d\n", sum);
      System.out.printf("평균 : %.2f\n", (float)(sum/3));
    }
}