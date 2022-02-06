package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kroch
 * 
 * @Aspect를 사용하려면 @EnableAspectJAutoProxy를 명시적으로 추가해야 하지만
 * 스프링 부트를 사용하면 @EnableAspectJAutoProxy가 자동으로 추가된다.
 *
 */
@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

}
