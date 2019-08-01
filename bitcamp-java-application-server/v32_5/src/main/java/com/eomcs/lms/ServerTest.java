package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");
    
    try (Socket socket = new Socket("192.168.0.79", 8888);
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
      
      out.writeUTF("add");
      out.flush();
      out.writeObject(member);
      System.out.println("add 요청함1");
      
      //서버가 보낸 데이터를 읽는다.
      String response = in.readUTF();
      System.out.println("-->"+response);
      
      //서버에 전송할 객체를 준비한다.
      member = new Member();
      member.setNo(2);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");
      
      out.writeUTF("add");
      out.flush();
      out.writeObject(member);
      System.out.println("add 요청함2");
      
      //서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("-->"+response);
      
      out.writeUTF("list");
      out.flush();
      System.out.println("list 요청함.");
      
      //서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("-->"+response);
      
      @SuppressWarnings("unchecked")
      List<Member> list = (List<Member>)in.readObject();
      System.out.println("---------------------");
      for(Member m : list) {
        System.out.println(m);
      }
      System.out.println("---------------------");
      
      //서버가 처리할 수 없는 명령어 보내기
      out.writeUTF("delete");
      out.flush();
      System.out.println("delete 요청함");
      
      //서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("-->"+response);
      
      response = in.readUTF();
      System.out.println("-->"+response);
      
      out.writeUTF("quit");
      out.flush();
      System.out.println("quit 요청함");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("서버와 연결 끊음.");

  }
}
