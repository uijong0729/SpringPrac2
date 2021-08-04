package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

    // 유레카 서버 작동 중에 기동시킬 것
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
