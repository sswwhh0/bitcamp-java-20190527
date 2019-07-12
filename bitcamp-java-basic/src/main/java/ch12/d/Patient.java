package ch12.d;

public class Patient {
  
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  
  String name;
  int age;
  int weight;
  int height;
  int gender;
  
  public String toString() {
    return String.format("이름 : %s, 나이 : %s, 키 : %s, 몸무게 : %s, 성별 : %s", 
        this.name, this.age, this.height, this.weight, this.gender);
  }
  
}
