package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardUpdateCommand implements Command {

  private PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoboard = photoBoardDao.findBy(no);
      
      if (photoboard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      String str = Input.getStringValue(in, out, "제목? ");
      
      if (str.length() > 0) {
        photoboard.setTitle(str);
        photoBoardDao.update(photoboard);
        out.println("데이터를 변경하였습니다.");
      } else {
        out.println("데이터 변경을 취소합니다");
      }
      
    } catch(Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }

  }
}
