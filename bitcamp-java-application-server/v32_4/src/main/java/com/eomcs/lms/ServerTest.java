package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");
    
    try (Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in =new ObjectInputStream(
            socket.getInputStream())) {
      //서버와의 입출력을 위해 스트림 객체를 준비한다.
      // -> 버퍼를 사용할 경우, 데이터를 보내는 쪽에서는 출력 스트림을 먼저 준비하라!
      
      
      System.out.println("서버와 연결되었음!");
      
      //서버에 전송할 객체를 준비한다.
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");
      
      //서버에 출력한다.
      out.writeObject(member);
      out.flush(); // 주의! 버퍼로 출력한 내용을 서버로 보내도록 강제해야 한다.
      System.out.println("서버에 데이터를 보냈음.");
      
      //서버가 보낸 데이터를 읽는다.
      String response = in.readUTF();
      System.out.println("서버로부터 데이터를 받았음.");
      
      //서버가 보낸 데이터를 콘솔창에 출력한다.
      System.out.println("-->"+response);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("서버와 연결 끊음.");

  }
}
