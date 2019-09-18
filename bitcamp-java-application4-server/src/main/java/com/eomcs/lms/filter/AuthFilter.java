package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 역할
// -> 로그인 사용자만 등록, 변경, 삭제를 수행할 수 있게 한다.

public class AuthFilter implements Filter {

  String[] path;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
    path = filterConfig.getInitParameter("path").split(",");
  }

  @Override
  public void doFilter(ServletRequest request, 
      ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp = (HttpServletResponse) response;

    String servletPath = httpReq.getServletPath();
    for(String p : path) {
      if(servletPath.endsWith(p)) {
        if(httpReq.getSession().getAttribute("loginUser") == null) {
          httpResp.sendRedirect("/auth/login");
          return;
        } else {
          break;
        }
      }
    }

    chain.doFilter(request, response);
  }
}
