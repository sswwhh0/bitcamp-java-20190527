// v32_14 : DAO 사용 규칙을 정의하여 일관성 있는 호출을 유도한다
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.servlet.BoardServlet;
import com.eomcs.lms.servlet.LessonServlet;
import com.eomcs.lms.servlet.MemberServlet;

public class ServerApp {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");
      System.out.println();
 
      try (Socket clientSocket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
        
        System.out.println("클라이언트와 연결되었음.");
        System.out.println();
        
        BoardServlet boardServlet = new BoardServlet(in, out);
        MemberServlet memberServlet = new MemberServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);
        System.out.println();
        
        while (true) {
          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();
          System.out.println(command + " 요청 처리중...");
          
          if (command.startsWith("/board/")) {
            boardServlet.service(command);
          }
          
          else if (command.startsWith("/member/")) {
            memberServlet.service(command);
          }
          
          else if (command.startsWith("/lesson/")) {
            lessonServlet.service(command);
          }
          
          else if (command.startsWith("quit")) {
            out.writeUTF("ok");
            out.flush();
            System.out.println("종료합니다!");
            System.out.println("-----------------");
            
            break;
          }
          
          else {
            out.writeUTF("fail");
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료!");
          System.out.println("-----------------");
        }
        // 클라이언트와 연결을 끊기 전에 작업 내용을 파일에 저장한다.
        boardServlet.saveData();
        memberServlet.saveData();
        lessonServlet.saveData();
        System.out.println("-----------------");
      } 
      System.out.println("클라이언트와 연결을 끊었음.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }
}






