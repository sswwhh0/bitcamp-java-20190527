package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;

@Component("/photoBoard/detail")
public class PhotoBoardDetailCommand {

  private PhotoBoardDao photoBoardDao;

  public PhotoBoardDetailCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @RequestMapping
  public void execute(BufferedReader in, PrintStream out) {

    try {
      //클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      photoBoardDao.increaseViewCount(no);
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("작성일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %s\n", photoBoard.getViewCount());
      out.printf("수업: %s\n", photoBoard.getLessonNo());
      out.println("사진 파일 : ");

      List<PhotoFile> files = photoBoard.getFiles();
      for(PhotoFile file : files) {
        out.printf("> %s\n", file.getFilePath());
      }

    } catch(Exception e) {
      out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }

  }
}
