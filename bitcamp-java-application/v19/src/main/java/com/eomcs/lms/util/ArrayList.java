package com.eomcs.lms.util;

import java.util.Arrays;

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
  
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if(index<0 || index>=size)
      throw new IndexOutOfBoundsException(String.format("인덱스 = %s", index));
    
    return (E) list[index];
  }
  
  @SuppressWarnings("unchecked")
  public E set(int index, E obj) {
    if(index<0 || index>=size)
      throw new IndexOutOfBoundsException(String.format("인덱스 = %s", index));
    
    E old = (E) list[index];
    list[index] = obj;
    
    return old;
  }
  
  public E remove(int index) {
    if(index<0 || index>=size)
      throw new IndexOutOfBoundsException(String.format("인덱스 = %s", index));
    
    @SuppressWarnings("unchecked")
    E old = (E) list[index];
    
    //방법 1 : 직접 반복문을 이용하여 삭제 처리하기
//    for(int i=index; i<size; i++) {
//      list[i] = list[i+1];
//    }
//    list[--size] = null;
    
    //방법 2 : 배열 복사 기능을 이용하여 삭제 처리하기
    System.arraycopy(list, index+1, list, index, this.size-(index+1));
    list[--size] = null;
    
    return old;
  }
  
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    
    String old = list.set(1, "수정");
    System.out.println("원래 값 : "+old);
    list.remove(1);
    
    for(int i=0; i<list.size(); i++) {
      System.out.println(list.get(i));
    }
    
  }
}
