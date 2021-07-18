package com.example.demo.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AppConfig;

public class ApplicationContextTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(ac.getBean(name));
		}
	}
	
	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(name);

			// BeanDefinition.ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
			// BeanDefinition.INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println(ac.getBean(name));
			} 
		}
	}
	
}
