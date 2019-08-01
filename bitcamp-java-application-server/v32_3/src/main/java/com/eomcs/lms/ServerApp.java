package com.eomcs.lms;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept()) {
        // 클라이언트 연결 객체(소켓)에서 I/O 스트림 객체를 얻는다.
        BufferedReader in =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));

        System.out.println("클라이언트와 연결되었음.");

        // 클라이언트가 보낸 데이터를 읽는다.
        // -> 보낸 규칙에 맞춰 읽어야 한다
        String message = in.readLine();

        // 클라이언트가 보낸 문자열을 서버로 콘솔에서 한 번 출력해보고
        System.out.println("-->" + message);

        // 클라이언트가 보낸 문자열을 그대로 리턴한다.
        out.println("[천재]" + message);
        out.flush(); // PrintWrite에서 출력한 버퍼에있는 데이터를
        System.out.println("클라이언트로 데이터를 보냈음");

      }

      System.out.println("클라이언트와 연결을 끊었음.");

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");

  }
}
