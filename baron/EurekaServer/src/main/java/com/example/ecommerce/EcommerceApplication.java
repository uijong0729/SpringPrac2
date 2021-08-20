package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// 유레카 서버 역할로서 기동
@EnableEurekaServer
public class EcommerceApplication {
    // 유레카 대시보드 http://localhost:8761/
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
