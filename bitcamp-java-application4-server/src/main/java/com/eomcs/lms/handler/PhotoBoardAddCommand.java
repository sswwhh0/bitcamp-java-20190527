package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardAddCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  Scanner scan;

  public PhotoBoardAddCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      PhotoBoard photoboard = new PhotoBoard();
      photoboard.setLessonNo(Input.getIntValue(in, out, "번호? "));
      photoboard.setTitle(Input.getStringValue(in, out, "제목? "));
      
      photoBoardDao.insert(photoboard);
      out.println("저장하였습니다.");
      
    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
