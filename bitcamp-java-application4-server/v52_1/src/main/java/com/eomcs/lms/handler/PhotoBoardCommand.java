package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;

@Component
public class PhotoBoardCommand {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Transactional
  @RequestMapping("/photoboard/add")
  public void add(BufferedReader in, PrintStream out) {

    try {
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

      out.println("모든 파일을 저장하였습니다.");
      out.flush();

    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Transactional
  @RequestMapping("/photoboard/delete")
  public void delete(BufferedReader in, PrintStream out) {
    try {
      int no = Input.getIntValue(in, out, "번호? ");

      if(photoBoardDao.findBy(no) == null) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      photoFileDao.deleteAll(no);
      photoBoardDao.delete(no);

      out.println("데이터를 삭제하였습니다.");
      out.flush();

    } catch(Exception e) {
      out.println("데이터 삭제에 실패했습니다!");
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/photoboard/detail")
  public void detail(BufferedReader in, PrintStream out) {


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

  @RequestMapping("/photoboard/list")
  public void list(BufferedReader in, PrintStream out) {
    //Board[] boards = new Board[boardList.size()];
    //boardList.toArray(boards);

    try {
      List<PhotoBoard> photoboards = photoBoardDao.findAll();
      for (PhotoBoard photoboard : photoboards) {
        out.printf("%d, %s, %s, %d, %d\n", 
            photoboard.getNo(), photoboard.getTitle(), 
            photoboard.getCreatedDate(), photoboard.getViewCount(), photoboard.getLessonNo());
      }
    } catch(Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @Transactional
  @RequestMapping("/photoboard/update")
  public void update(BufferedReader in, PrintStream out) {
    try {
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoboard = photoBoardDao.findBy(no);

      if (photoboard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      // 사용자로부터 변경할 값을 입력 받는다.
      PhotoBoard data = new PhotoBoard();
      data.setNo(no);

      out.println("제목을 입력하지 않으면 이전 제목을 유지합니다");
      String str = Input.getStringValue(in, out, String.format("제목(%s)? ", photoboard.getTitle()));

      if(str.length() > 0) {
        data.setTitle(str);
        photoBoardDao.update(data);
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

      out.println("모든 데이터를 변경하였습니다");
      out.flush();

    } catch(Exception e) {
      try {
      } catch(Exception e2) {
      }
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    }
  }

}
