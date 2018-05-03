package com.lyu.auth.controller;

import com.lyu.auth.config.properties.SystemProperties;
import com.lyu.auth.config.properties.WechatProperties;
import com.lyu.auth.entity.weixin.AccessToken;
import com.lyu.auth.entity.weixin.ErrorEntity;
import com.lyu.auth.service.WechatService;
import com.lyu.auth.util.json.JsonHelper;
import com.lyu.auth.util.keygen.KeyGeneric;
import com.lyu.auth.util.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth2/wechat")
public class WechatController {

  private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

  @Autowired private WechatService wechatService;

  @Autowired private RedisCache redisCache;

  @Autowired private WechatProperties wechatProperties;

  @Autowired private SystemProperties systemProperties;

  @RequestMapping(value = "/authorize")
  public void authorize(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String key = String.valueOf(new KeyGeneric(1, 1).nextId());
    String url = wechatProperties.getAuthorizeUrl();
    String rUrl = systemProperties.getReturnUrl();
    rUrl = URLEncoder.encode(String.format(rUrl, key), "UTF-8");
    url = String.format(url, wechatProperties.getAppid(), rUrl, key);

    String servHost = request.getParameter("servHost");
    String servPort = request.getParameter("servPort");
    String uri = request.getParameter("uri");

    redisCache.setString(key, "uri:" + uri + ";host:" + servHost + ";port:" + servPort);

    if (logger.isDebugEnabled()) {
      logger.debug("请求的uri是：{}", uri);
      logger.debug("生成的key是：{}", key);
      logger.debug("生成的rUrl是：{}", rUrl);
      logger.debug("生成的Url是：{}", url);
    }

    response.setHeader("Location", url);
    response.setStatus(302);
  }

  @RequestMapping(value = "/notifyUrl")
  public void notifyUrl(HttpServletRequest request, HttpServletResponse response) {
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    String key = request.getParameter("key");

    String uri = redisCache.getString(key);
    String rUrl = systemProperties.getRedirectUrl();
    rUrl =
        String.format(
            rUrl,
            uri.split(";")[1].split(":")[1],
            uri.split(";")[2].split(":")[1],
            uri.split(";")[0].split(":")[1],
            code);

    uri = uri + ";" + "code:" + code;

    redisCache.setString(key, uri);
    redisCache.expire(key, 300000);

    response.setHeader("Location", rUrl);
    response.setStatus(301);
  }

  @RequestMapping(
    value = "/getToken",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Map<String, Object> getToken(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> result = new HashMap<>();
    String code = request.getParameter("code");
    String json =
        wechatService.getAccessToken(
            wechatProperties.getAppid(),
            wechatProperties.getSecret(),
            code,
            wechatProperties.getGrantType());

    try {
      AccessToken token = JsonHelper.readObject(json, AccessToken.class);
      if (token.getAccessToken() == null) {
        ErrorEntity errorEntity = JsonHelper.readObject(json, ErrorEntity.class);
        result.put("result", false);
        result.put("msg", errorEntity.getErrmsg());
      } else {
        result.put("result", true);
        result.put("data", token);
      }
    } catch (IOException e) {
      result.put("result", false);
      result.put("msg", e.getMessage());
    }
    return result;
  }
}
