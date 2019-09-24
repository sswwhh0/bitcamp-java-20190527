package com.eomcs.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageController {
  public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws Exception;
}
