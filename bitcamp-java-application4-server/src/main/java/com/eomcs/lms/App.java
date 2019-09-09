// v55_1 : Servlet 컨테이너 도입하기
package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;
import com.eomcs.util.RequestMappingHandlerMapping.RequestHandler;

@WebServlet("/*")
public class App implements Servlet {

  private final static Logger logger = LogManager.getLogger(App.class);

  ApplicationContext appCtx;
  RequestMappingHandlerMapping handlerMapping;
  int state;
  ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {

    this.config = config;

    // 톰캣 서버가 이 객체를 사용하기 전에 이 객체가 작업에 필요한 자원들을 
    // 준비할 수 있도록 이 메서드를 호출한다.

    appCtx = new AnnotationConfigApplicationContext(AppConfig.class);

    // SPring IoC 컨테이너에 들어 있는(Spring IoC 컨테이너가 생성한) 객체 알아내기
    String[] beanNames = appCtx.getBeanDefinitionNames();
    logger.debug("[Spring IoC 컨테이너 객체들]------------");

    for(String beanName : beanNames) {
      logger.debug(String.format("%s(%s)",appCtx.getBean(beanName).getClass().getSimpleName(),
          beanName));
    }
    logger.debug("-------------------");

    handlerMapping = createRequestMappingHandlerMapping();
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    // 클라이언트가 요청한 정보를 자세하게 뽑기 위해 파라미터 객체를
    // 원래의 타입으로 형변환 한다.

    HttpServletRequest httpReq = (HttpServletRequest) req;
    String command = httpReq.getPathInfo();
    System.out.println(command);
    logger.info(command);
    logger.info(httpReq.getQueryString());

    try {
      RequestHandler requestHandler = 
          handlerMapping.getRequestHandler(command);
      
      res.setContentType("text/html;charset=UTF-8");

      if(requestHandler != null) {
        //클라이언트 요청을 처리하기 위해 메서드를 호출한다
        requestHandler.method.invoke(
            requestHandler.bean, req, res);
      } else {
        PrintWriter out = res.getWriter();
        out.println("<html><body><h1>"
            + "해당 명령을 처리할 커맨드를 찾을 수 없습니다.</h1></body></html>");
        logger.info("실패! - "+command);
      }

    } catch(Exception e) {
      PrintWriter out = res.getWriter();
      out.println("<html><body><h1>"
          + "해당 명령을 찾을 수 없습니다.catch</h1></body></html>");

      logger.info("클라이언트 요청 처리 중 오류 발생!");
      StringWriter out2 = new StringWriter();
      e.printStackTrace(new PrintWriter(out2));
      logger.debug(out2.toString());
    }
  }

  @Override
  public void destroy() {
    // 톰캣 서버가 종료되기 전에 서블릿들이 이 객체가 준비한 자원들을
    // 해제할 수 있도록 이 메서드를 호출한다.
  }

  @Override
  public String getServletInfo() {
    // 톰캣 서버가 관리자 페이지에 애플리케이션에 대해
    // 간단한 소개를 출력하기 위해 이 메서드를 호출한다.
    // 즉, 이 메서드의 리턴 값을 관리자 페이지에 출력한다.
    return "수업 관리 시스템";
  }

  @Override
  public ServletConfig getServletConfig() {
    // 이 객체를 실행하면서 이 객체의 실행 환경 정보를 알고 싶을 때
    // 이 메서드를 호출한다. 리턴 값은 init()메서드에서 파라미터 값을 잘 보관해야 한다.
    return this.config;
  }

  private RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
    RequestMappingHandlerMapping mapping = 
        new RequestMappingHandlerMapping();

    // 객체풀에서 @Component 애노테이션이 붙은 객체 목록을 꺼낸다
    Map<String, Object> components = appCtx.getBeansWithAnnotation(Component.class);

    logger.trace("[요청 핸들러]---------------");

    //객체 안에 선언된 메서드 중에 @RequestMapping이 붙은 메서드를 찾아낸다.
    Collection<Object> objList = components.values();

    objList.forEach(obj -> {

      Method[] methods = null;

      if(AopUtils.isAopProxy(obj)) { // 원본이 아니라 프록시 객채라면 ex.photoboard
        // 프록시 객체의 클래스가 아니라 원본 객체의 클래스 정보를 가져온다.
        try {
          Class<?> originClass = (Class<?>)obj.getClass().getMethod(
              "getTargetClass").invoke(obj);
          methods = originClass.getMethods();
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        // 객체에서 메서드 정보를 추출한다.
        methods = obj.getClass().getMethods();
      }

      for(Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class);
        if(anno == null)
          continue;

        // @RequestMapping이 붙은 메서드를 찾으면 mapping객체에 보관한다.
        mapping.addRequestHandler(anno.value()[0], obj, m);
        logger.trace(String.format("%s ==> %s.%s()", anno.value()[0],
            obj.getClass().getSimpleName(), m.getName()));
      }
    });

    return mapping;
  }

}










