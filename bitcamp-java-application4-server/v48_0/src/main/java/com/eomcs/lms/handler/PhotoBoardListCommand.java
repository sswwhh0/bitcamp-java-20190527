package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Component;
import com.eomcs.util.RequestMapping;

@Component("/photoBoard/list")
public class PhotoBoardListCommand {
  
  private PhotoBoardDao photoBoardDao;

  public PhotoBoardListCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  @RequestMapping
  public void execute(BufferedReader in, PrintStream out) {
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

}
