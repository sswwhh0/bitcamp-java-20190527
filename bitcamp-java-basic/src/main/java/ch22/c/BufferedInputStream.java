package ch22.c;

import java.io.IOException;
import java.io.InputStream;

// InputStream에 기능을 덧붙이는 플러그인 역할을 수행하는 클래스이다.
// => 이런 클래스를 데코레이터(decorator)라 한다.
// => 데코레이터는 기능을 덧붙이는 대상 클래스와 같은 조상을 가져야 한다.
//    그리고 생성자에게 대상 객체 주소를 받아야 한다.
//    작업을 수행할 때 대상 객체를 사용한다.
//    그리고 자신만의 기능을 덧붙인다.
public class BufferedInputStream extends InputStream {
  
  InputStream in;
  byte[] buf = new byte[1024];
  int size = 0;
  int cursor = 0;
  
  public BufferedInputStream(InputStream in) {
    this.in = in;
  }
  
  public int read() throws IOException {
    if (cursor >= size) { //버퍼에 보관된 데이터를 다 읽었으면,
      size = in.read(buf); //다시 파일인풋스트림을 사용하여 1024바이트를 읽어 온다.
      if (size == -1) //물론 파일에 읽은 데이터가 없다면, 즉 다 읽었다면 -1을 리턴한다.
        return -1;
      cursor = 0; // 파일인풋스트림을 사용하여 새로 1024바이트를 읽어 버퍼에 저장했다면,
                  // 다시 컷허의 위치를 0으로 설정한다.
    }
    
    // 바이트의 값을 온전히 4바이트 int 값으로 변환하기 위해
    // 0x000000ff 값을 & 비트 연산한다.
    // => 0xff 상수 값은 0x000000ff 를 의미한다.
    return buf[cursor++] & 0xff;
  }
  
}






