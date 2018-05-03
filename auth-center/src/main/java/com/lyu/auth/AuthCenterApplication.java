package com.lyu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthCenterApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthCenterApplication.class, args);
  }
}
