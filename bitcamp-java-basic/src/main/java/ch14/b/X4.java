package ch14.b;

public class X4 extends X3{
  
  @Override
  void m1() {
    System.out.println("X4.m1()");
  }
  
  void test() {
    this.m1();
    super.m1();
  }
  
}
