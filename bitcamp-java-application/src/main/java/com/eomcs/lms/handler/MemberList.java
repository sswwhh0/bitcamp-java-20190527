package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  private Member[] list = new Member[100];
  private int size = 0;
  
  public void add(Member member) {
    this.list[this.size++] = member;
  }
  
  public Member[] toArray() {
    Member[] arr = new Member[this.size];
    for(int i=0; i<this.size;i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }
}
