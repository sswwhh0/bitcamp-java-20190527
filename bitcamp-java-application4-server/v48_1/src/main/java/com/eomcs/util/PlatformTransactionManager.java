package com.eomcs.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

// Mybatis의 SqlSessionFactory 객체를 사용하여 트랜잭션을 관리하는 일을 한다.
public class PlatformTransactionManager {

  private SqlSessionFactory sqlSessionFactory;

  public PlatformTransactionManager(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public void beginTransaction() throws Exception {
    // 여기에서 스레드가 사용할 SqlSession 객체를 미리 준비한다.
    ((SqlSessionFactoryProxy)sqlSessionFactory).prepareSessionInThread();
  }

  public void commit() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.commit();
    ((SqlSessionProxy)sqlSession).realClose();
  }

  public void rollback() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.rollback();
    ((SqlSessionProxy)sqlSession).realClose();
  }

}
