package com.lyu.auth.service;

import com.lyu.auth.config.feign.FullLogConfiguration;
import com.lyu.auth.entity.alipay.AlipayOpenAuthTokenAppResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
  name = "alipayservice",
  url = "https://openapi.alipay.com",
  configuration = FullLogConfiguration.class
)
public interface AlipayService {

  @RequestMapping(
    value = "/gateway.do",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  AlipayOpenAuthTokenAppResponse MediaTypegetAuthToken();
}
