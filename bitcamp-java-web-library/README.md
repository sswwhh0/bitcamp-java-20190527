# bitcamp-java-web-library
bitcamp-java-spring-webmvc 프로젝트에서 사용할 라이브러리(.jar)를 생성한다.

## src.01

- ServletContainerInitializer 구현체를 만든다.
- /META-INF/services/java.servlet.ServletContainerIntializer 파일 생성
- 해당 파일에 구현체 클래스의 이름을 등록한다.
- gradle build를 실행하여 .jar 파일을 생성한다.
- .jar파일을 웹 애플리케이션 프로젝트의 import한 다음에 서블릿 컨테이너를 실행하라
- 콘솔 창에 onStartup() 메서드가 호출된 것을 확인할 수 있을 것이다.

##src.02

- onStartup()이 호출될 때 특정 타입의 클래스 목록을 받을 수 있다.
- 클래스 @HandlesTypes(타입) 애노테이션을 붙인다.
- onStartup의 types 파라미터 값을 사용한다.