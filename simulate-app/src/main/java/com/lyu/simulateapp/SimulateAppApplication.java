package com.lyu.simulateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimulateAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimulateAppApplication.class, args);
  }
}
