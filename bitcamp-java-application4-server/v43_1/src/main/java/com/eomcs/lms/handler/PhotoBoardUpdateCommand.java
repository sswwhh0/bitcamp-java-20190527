package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;
import com.eomcs.util.PlatformTransactionManager;

public class PhotoBoardUpdateCommand implements Command {

  private PlatformTransactionManager txManager;
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardUpdateCommand(PlatformTransactionManager txManager, PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      txManager.beginTransaction();

      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoboard = photoBoardDao.findBy(no);

      if (photoboard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      out.println("제목을 입력하지 않으면 이전 제목을 유지합니다");
      String str = Input.getStringValue(in, out, String.format("제목(%s)? ", photoboard.getTitle()));

      if(str.length() > 0) {
        photoboard.setTitle(str);
        photoBoardDao.update(photoboard);
      }

      out.println("사진 파일 : ");
      List<PhotoFile> files = photoFileDao.findAll(no);
      for(PhotoFile file : files) {
        out.printf("> %s\n", file.getFilePath());
      }

      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다");
      String response = Input.getStringValue(in, out, "사진을 변경하시겠습니까? (Y/N)");

      if(!response.equalsIgnoreCase("y")) {
        out.println("파일 변경을 취소합니다");
        return;
      }
      // 기존 사진 파일을 삭제한다.
      photoFileDao.deleteAll(no);

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
        count++;
        out.println("데이터를 변경하였습니다");
      } // while

      txManager.commit();
      out.println("모든 데이터를 변경하였습니다");
      out.flush();

    } catch(Exception e) {
      try {
        txManager.rollback();
      } catch(Exception e2) {
      }
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }

  }
}
