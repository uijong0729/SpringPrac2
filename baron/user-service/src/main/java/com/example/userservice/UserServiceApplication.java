package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.error.FeignErrorDecoder;

import feign.Logger;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {

    // 유레카 서버 작동 중에 기동시킬 것
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    
    /**
     * @return
     * 
     * @LoadBalanced
     * 	yml파일의 http://127.0.0.1:8000/order-service/%s/orders 의 IP주소 부분을
     * 	http://ORDER_SERVICE/order-service/%s/orders 으로 서비스명으로 대체하여 IP나 포트의 변경에도 대응가능해진다.
     * 
     * @Deprecated
     *  이 프로젝트에서는 Spring Cloud의 FeignClient로 대체한다.
     */
    @Bean
    @LoadBalanced
    @Deprecated
    public RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }
    
    @Bean
    public Logger.Level feignLoggerLevel(){
    	return Logger.Level.FULL;
    }
    
//    이미 @Component로 등록했기 때문에 의미없음
//
//    @Bean
//    public FeignErrorDecoder feignErrorDecoder() {
//    	return new FeignErrorDecoder();
//    }

}
