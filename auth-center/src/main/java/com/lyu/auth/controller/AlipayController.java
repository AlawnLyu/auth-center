package com.lyu.auth.controller;

import com.lyu.auth.service.AlipayService;
import com.lyu.auth.util.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/oauth2/alipay")
public class AlipayController {

  private static final Logger logger = LoggerFactory.getLogger(AlipayController.class);

  @Autowired private AlipayService alipayService;

  @Autowired private RedisCache redisCache;
}
