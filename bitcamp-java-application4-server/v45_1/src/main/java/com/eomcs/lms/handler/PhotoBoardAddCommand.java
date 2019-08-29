package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;
import com.eomcs.util.PlatformTransactionManager;

public class PhotoBoardAddCommand implements Command {

  private PlatformTransactionManager txManager;
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardAddCommand(PlatformTransactionManager txManager, PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      txManager.beginTransaction();

      PhotoBoard photoboard = new PhotoBoard();
      photoboard.setTitle(Input.getStringValue(in, out, "제목? "));
      photoboard.setLessonNo(Input.getIntValue(in, out, "수업? "));
      photoBoardDao.insert(photoboard);

      out.println("최소 한 개의 사진 파일을 등록해야 합니다");
      out.println("파일명 입력 없이 엔터를 치면 파일 추가를 마칩니다.");
      out.flush();
      int count = 0;

      while(true) {
        String filepath = Input.getStringValue(in, out, "사진 파일? ");

        if (filepath.length() == 0) {
          if(count > 0) {
            break;
          } else {
            out.println("최소 한 개의 사진 파일을 등록해야 합니다");
            continue;
          }
        }

        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoboard.getNo());

        photoFileDao.insert(photoFile);

        // -> DMBS 쪽 담당자(스레드)에게 임시 보관된 데이터 변경 결과를
        //    실제 테이블에 적용할 것을 명령한다.
        count++;
        out.println("저장하였습니다.");
      }

      txManager.commit();
      out.println("모든 파일을 저장하였습니다.");
      out.flush();

    } catch (Exception e) {
      try {
        // -> DMBS 쪽 담당자(스레드)에게 임시 보관된 데이터 변경 결과를 모두 취소할 것을 명령한다.
        txManager.rollback();
      } catch(Exception e2) {
      }
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }

  }
}
