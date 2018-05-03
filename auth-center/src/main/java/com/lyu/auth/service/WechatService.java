package com.lyu.auth.service;

import com.lyu.auth.config.feign.FullLogConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
  name = "weixinservice",
  url = "${wechat.apiHost}",
  configuration = FullLogConfiguration.class
)
public interface WechatService {

  @RequestMapping(value = "/sns/oauth2/access_token", produces = MediaType.APPLICATION_JSON_VALUE)
  String getAccessToken(
      @RequestParam("appid") String appid,
      @RequestParam("secret") String secret,
      @RequestParam("code") String code,
      @RequestParam(name = "grant_type", required = false, defaultValue = "authorization_code")
          String grant_type);
}
