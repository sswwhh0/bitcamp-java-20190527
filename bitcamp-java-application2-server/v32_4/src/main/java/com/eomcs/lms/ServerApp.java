package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();
          ObjectOutputStream out = new ObjectOutputStream(
              clientSocket.getOutputStream());
          ObjectInputStream in =new ObjectInputStream(
              clientSocket.getInputStream())) {
        
        System.out.println("클라이언트와 연결되었음.");

        // 클라이언트가 보낸 serialized된 인스턴스 데이터를 읽는다.
        Member member = (Member)in.readObject();
        System.out.println("클라이언트가 객체를 읽었음.");

        // 클라이언트가 보낸 문자열을 서버로 콘솔에서 한 번 출력해보고
        System.out.println(member);

        // 클라이언트가 보낸 문자열을 그대로 리턴한다.
        out.writeUTF("ok");
        out.flush();
        
        System.out.println("클라이언트로 데이터를 보냈음");

      }

      System.out.println("클라이언트와 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");

  }
}
