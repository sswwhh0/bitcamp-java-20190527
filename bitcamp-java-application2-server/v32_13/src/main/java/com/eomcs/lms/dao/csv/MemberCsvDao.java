package com.eomcs.lms.dao.csv;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberCsvDao extends AbstractCsvDataSerializer<Member, Integer> {
  
  public MemberCsvDao(String file) throws ClassNotFoundException {
    super(file);
    
    try {
      loadData();
      System.out.println("Member게시물 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("Member게시물 데이터 로딩 중 오류 발생!");
    }
  }

  @Override
  public void saveData() {
    try {
      super.saveData();
      System.out.println("Member게시물 데이터 저장 완료!");
    } catch (FileNotFoundException e) {
      System.out.println("Member파일을 생성할 수 없습니다!");
    } catch (Exception e) {
      System.out.println("Member파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }
  
  @Override
  protected Member createObject(String[] values) {
    //파일에서 읽어 들인 데이터의 CSV 형식은 다음과 같다고 가정하자!
    //-> 번호,내용,생성일,조회수
    //
    Member member = new Member();
    member.setNo(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setEmail(values[2]);
    member.setPassword(values[3]);
    member.setPhoto(values[4]);
    member.setTel(values[5]);
    member.setRegisteredDate(Date.valueOf(values[6]));
    
    
    return member;
  }
  
  @Override
  protected String createCSV(Member obj) {
    return String.format("%d,%s,%s,%d", obj.getNo(),
        obj.getName(), obj.getEmail(), obj.getPassword(), obj.getPhoto(),
        obj.getTel(), obj.getRegisteredDate());
  }
  
  @Override
  public int indexOf(Integer key) {
    int i = 0;
    for (Member obj : list) {
      if (obj.getNo() == key) {
        return i;
      }
      i++;
    }
    return -1;
  }

  public int insert(Member member) throws Exception {
    list.add(member);
    return 1;
  }

  public List<Member> findAll() throws Exception {
    return list; 
  }

  public Member findBy(int no) throws Exception {
    int index = indexOf(no);
    if(index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Member member) throws Exception {
    int index = indexOf(member.getNo());
    if(index == -1) {
      return 0;
    }
    list.set(index, member);
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if(index == -1) {
      return 0;
    }
    
    list.remove(index);
    return 1;
  }

}
