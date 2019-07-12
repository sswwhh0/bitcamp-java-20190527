package ch12.d;

public class Patient2 {
  
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  
  private String name;
  private int age;
  private int weight;
  private int height;
  private int gender;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if(age>0 && age<150)
      this.age = age;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    if(weight>0 && height<500)
      this.weight = weight;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    if(height>1 && height<300)
      this.height = height;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    if(gender>0 && gender<3)
      this.gender = gender;
  }

  public String toString() {
    return String.format("이름 : %s, 나이 : %s, 키 : %s, 몸무게 : %s, 성별 : %s", 
        this.name, this.age, this.height, this.weight, this.gender);
  }
  
}
