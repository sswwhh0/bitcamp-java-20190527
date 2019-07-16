package com.eomcs.lms.util;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class ArrayList<E> {
  private static int DEFAULT_CAPACITY = 100;
  private Object[] list;
  private int size = 0;
  
  public ArrayList() {
    this(DEFAULT_CAPACITY); //생성자에서 다른 생성자를 호출할 수 있다.
  }
  
  public ArrayList(int initialCapacity) {
    if(initialCapacity<50 || initialCapacity>10000)
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[initialCapacity];
  }
  
  public void add(E obj) {
    if(this.size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(this.list, newCapacity);
    }
    else {
      this.list[this.size++] = obj;
    }
  }
  
  public Object[] toArray() {
    return Arrays.copyOf(this.list,  this.size);
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if(a.length<this.size) {
      //파라미터로 넘겨 받은 배열의 크기가 저장된 데이터의 개수 보다 작다면
      //이 메서드에서 새 배열을 만든다.
      return (E[]) Arrays.copyOf(list, this.size, a.getClass()); //세 번째 파라미터로 지정한 타입의 배열이 생성된다.
    }
    System.arraycopy(this.list, 0, a, 0, this.size);
    if(a.length>this.size)
      a[this.size]= null; 
    return a;
  }
  
  public int size() {
    return this.size;
  }
  
  public static void main(String[] args) {
    ArrayList<Board> boards = new ArrayList<>();
    
    Board b = new Board();
    b.setContents("aaaa");
    boards.add(b);
    
    b = new Board();
    b.setContents("bbbb");
    boards.add(b);
    
    b = new Board();
    b.setContents("cccc");
    boards.add(b);
    
    Board[] boards2 = new Board[3];
    boards.toArray(boards2);
    
    for(Board board : boards2) {
      System.out.println(board.getContents());
    }
    
//    Object[] arr = boards.toArray();
//    for(Object obj : arr) {
//      Board board = (Board) obj;
//      System.out.println(board.getContents());
//    }
//    
//    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
//    lessons.add(new Lesson());
//    
//    ArrayList<Member> members = new ArrayList<Member>();
//    members.add(new Member());
//    //members.add(new Lesson()); 컴파일 오류!
    
  }
}
