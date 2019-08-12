package com.eomcs.lms;

import java.io.IOException;
import java.net.Socket;

public class ServerTest {
  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    // 서버에 통신을 연결한다
    // -> new Socket(인터넷 주소, 포트번호)
    
    // 인터넷 주소?
    // -> IP주소 (예 : 192.168.0.1)
    // -> 도메인 이름 (예 : www.okok.com)
    
    // localhost?
    // -> 로컬 PC를 가리키는 특수 도메인 이름이다.
    
    // 127.0.0.1?
    // -> 로컬 PC를 가리키는 특수 IP주소이다.
    
    try (Socket socket = new Socket("192.168.0.51", 8888)) {
      System.out.println("서버와 연결되었음!");
    } catch (IOException e) {
      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 호출 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음.");

  }
}
