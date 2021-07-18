package com.example.demo.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo.AppConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.FilterType.ANNOTATION;

public class ComponentFilterAppConfigTest {

	@Test
	void filterScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		
		// includeFilters에 지정된 빈 (컴포넌트 스캔에 직접 추가)
		assertThat(ac.getBean("beanA", BeanA.class)).isNotNull();

		// excludeFilters에 지정된 빈 (컴포넌트 스캔에서 제외)
		assertThrows(NoSuchBeanDefinitionException.class, () -> {
			ac.getBean("beanB", BeanB.class);
		});
	}

	@Configuration
	@ComponentScan(includeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = MyIncludeComponent.class), excludeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = MyExcludeComponent.class))
	static class ComponentFilterAppConfig {

	}
}
