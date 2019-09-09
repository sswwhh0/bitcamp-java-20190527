package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class HelloCommand {
  
  @RequestMapping("/hello")
  public void execute(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><body><h1>안녕?</h1>");
    out.printf("v1 -> %s <br>\n", request.getParameter("v1"));
    out.printf("v2 -> %s <br>\n", request.getParameter("v2"));
    out.printf("v3 -> %s <br>\n", request.getParameter("v3"));
    out.println("</body></html>");
    
  }
}
