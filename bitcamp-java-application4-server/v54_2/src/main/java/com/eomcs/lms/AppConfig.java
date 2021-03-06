package com.eomcs.lms;

import org.springframework.context.annotation.ComponentScan;

// Spring IoC 컨테이너에게 알려줄 설정 정보를 애노테이션을 활용해
// 이 클래스에 저장해 준다.

// com.eomcs.lms 패키지에서 @Component가 붙은 클래스를 찾아 인스턴스를 자동으로 생성
@ComponentScan("com.eomcs.lms")
public class AppConfig {
  
}
