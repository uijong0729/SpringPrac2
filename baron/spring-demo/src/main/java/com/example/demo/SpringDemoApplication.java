package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 기본적으로 컴포넌트 스캔은 이 클래스가 포함되는 하위 패키지를 스캔한다.
 *
 */
@SpringBootApplication
public class SpringDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
