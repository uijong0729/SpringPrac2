package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
		// 기존 예제 코드를 남기기 위해 애노테이션 Configuration를 갖는 클래스들은 컴포넌트 스캔에서 제외시킨다.
		excludeFilters = @ComponentScan.Filter(
				type = FilterType.ANNOTATION, classes = Configuration.class
				),
		// 컴포넌트 스캔 탐색위치
		basePackages = "com.example.demo"
		)
public class AutoAppConfig {

}
