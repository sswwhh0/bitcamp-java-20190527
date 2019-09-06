package com.eomcs.util;

import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// HTTP 클라이언트가 요청한 정보를 갖고 있다.
public class ServletRequest {

  private static final Logger logger = 
      LogManager.getLogger(ServletRequest.class);

  String uri;
  HashMap<String, String> params = new HashMap<>();

  public void setUri(String uri) {
    this.uri = uri;
    
    try {
      List<NameValuePair> nvList = new URIBuilder(uri).getQueryParams();
      for(NameValuePair nv : nvList) {
        params.put(nv.getName(), nv.getValue());
        logger.debug(String.format("%s -> %s", nv.getName(), nv.getValue()));
      }
    } catch (Exception e) {
    }
  }
  
  public String getUri() {
    return this.uri;
  }
  
  public String getParameter(String name) {
    return params.get(name);
  }

}
