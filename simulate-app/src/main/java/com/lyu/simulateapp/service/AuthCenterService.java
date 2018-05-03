package com.lyu.simulateapp.service;

import com.lyu.simulateapp.config.CustomFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
  name = "authcenter",
  url = "http://www.lyu.com.cn",
  configuration = CustomFeignConfiguration.class
)
public interface AuthCenterService {

  @RequestMapping(
    value = "/oauth2/wechat/getToken",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Map<String, Object> getToken(@RequestParam("code") String code);
}
