package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;

public class PhotoBoardAddCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardAddCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      PhotoBoard photoboard = new PhotoBoard();
      photoboard.setLessonNo(Input.getIntValue(in, out, "번호? "));
      photoboard.setTitle(Input.getStringValue(in, out, "제목? "));

      while(true) {
      PhotoFile photoFile = new PhotoFile();
      String str = Input.getStringValue(in, out, "사진 파일? ");
      
        if (str.length() > 0) {
          photoFile.setFilePath(str);
          photoBoardDao.insert(photoboard);
          out.println("저장하였습니다.");
          break;
        } else {
          out.println("최소 한 개의 사진을 등록해야합니다");
        }
      }

    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
