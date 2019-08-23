package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.ConnectionFactory;
import com.eomcs.util.Input;

public class PhotoBoardDeleteCommand implements Command {

  private ConnectionFactory conFactory;
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardDeleteCommand(ConnectionFactory conFactory, PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.conFactory = conFactory;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    Connection con = null;

    try {

      con = conFactory.getConnection();
      con.setAutoCommit(false);
      
      int no = Input.getIntValue(in, out, "번호? ");

      if(photoBoardDao.findBy(no) == null) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      photoFileDao.deleteAll(no);
      photoBoardDao.delete(no);

      con.commit();
      out.println("데이터를 삭제하였습니다.");
      out.flush();

    } catch(Exception e) {
      try {
        con.rollback();
      } catch(Exception e2) {
      }
      out.println("데이터 삭제에 실패했습니다!");
      System.out.println(e.getMessage());
    } finally {
      try {
        con.setAutoCommit(true);
      } catch(Exception e) {
      }
    }

  }
}
