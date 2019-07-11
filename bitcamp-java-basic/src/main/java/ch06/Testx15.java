package ch06;

public class Testx15 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.printf("이름 : %s\n", args[0]);

    int sum = 0;
    for(int i=1; i<args.length; i++) {
      sum += Integer.parseInt(args[i]);
    }
    
    System.out.printf("총점 : %d\n", sum);
    System.out.printf("평균 : %.2f\n", (float)(sum/args.length-1));
  }

}
