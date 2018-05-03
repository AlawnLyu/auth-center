package com.lyu.simulateapp.controller;

import com.lyu.simulateapp.service.AuthCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

  private static final Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private AuthCenterService authCenterService;

  @RequestMapping("/pay")
  public Map<String, Object> pay(HttpServletRequest request, HttpServletResponse response) {
    // TODO: 通过token或其他条件判断是否进行过授权操作
    String code = request.getParameter("code");
    if (code == null || code.equals("")) {
      String uri = request.getRequestURI();
      if (logger.isDebugEnabled()) {
        logger.debug("请求的uri：{}", uri);
      }
      response.setStatus(302);
      try {
        String serverHost = request.getServerName();
        int serverPort = request.getServerPort();
        uri = URLEncoder.encode(uri, "UTF-8");
        if (logger.isDebugEnabled()) {
          logger.debug("编码后的uri：{}", uri);
        }
        response.setHeader(
            "Location",
            "http://www.lyu-tech.com.cn/oauth2/wechat/authorize?uri="
                + uri
                + "&servHost="
                + serverHost
                + "&servPort="
                + serverPort);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      return null;
    }
    Map<String, Object> tokenResult = authCenterService.getToken(code);
    Map<String, Object> result = new HashMap<>();
    result.put("result", tokenResult.get("result"));
    if ((boolean) tokenResult.get("result")) {
      result.put("msg", "请求成功");
      result.put("data", tokenResult.get("data"));
    } else {
      result.put("msg", tokenResult.get("msg"));
    }
    return result;
  }
}
