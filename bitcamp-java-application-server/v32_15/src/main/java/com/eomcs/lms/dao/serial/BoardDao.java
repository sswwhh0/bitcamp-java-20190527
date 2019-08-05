package com.eomcs.lms.dao.serial;

import java.util.List;
import com.eomcs.lms.domain.Board;

public interface BoardDao {

  public int insert(Board board) throws Exception;
  public List<Board> findAll() throws Exception;
  public Board findBy(int no) throws Exception;
  public int update(Board board) throws Exception;
  public int delete(int no) throws Exception;
  
}
