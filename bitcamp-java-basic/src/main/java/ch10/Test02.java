// 인스턴스 멤버 
package ch10;

class My0{
  //[스태틱 멤버]
  //스태틱 필드
  static int f1;
  
  static {
    //스태틱 초기화 블록
  }
  
  static void m1() {
    //스태틱 메서드
  }
  
  //[인스턴스 멤버]
  //인스턴스 필드
  int f2;
  
  {
    //인스턴스 초기화 블록
  }
  
  void m2() {
    //인스턴스 메서드
  }
  
  My0(){
    //생성자
  }
  
}

class My2 {
  //1) 인스턴스 필드
  int a = 100;
  
  //2) 인스턴스 블록
  //=> 인스턴스 변수를 모두 생성하고, 초기화 문장까지 실행한 후 "인스턴스 블록"이 실행된다.
  //=> 인스턴스 블록은 선언된 순서대로 실행된다.
  //=> 인스턴스 블록도 인스턴스 메서드와 마찬가지로 인스턴스의 주소를 담고 있는 this 라는 변수가 
  //   내장되어 있다. 
  {
    System.out.println("인스턴스 블록 실행! => a=" + this.a);
  }
  
  {
    System.out.println("여러 개의 인스턴스 블록을 가질 수 있다.");
  }
  
  //3) 생성자
  //=> 인스턴스 블록을 실행한 후 자동으로 호출된다.
  //=> 생성자에도 인스턴스 주소를 담는 this라는 내장 변수가 있다.
  My2() {
    System.out.println("생성자 호출: a=" + this.a);
  }
  
  //4) 인스턴스 메서드
  //=> 인스턴스 레퍼런스를 통해 호출한다.
  void m1() {
    System.out.println("인스턴스 메서드: m1()");
  }
}

public class Test02 {
  public static void main(String[] args) {
    My2 obj = new My2();
    System.out.println(obj.a);
  }
}
















