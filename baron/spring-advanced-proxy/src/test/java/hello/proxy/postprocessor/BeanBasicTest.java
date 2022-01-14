package hello.proxy.postprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

public class BeanBasicTest {

	@Test
	void basicConfig() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);
		
		// A는 빈으로 등록된다.
		A a = ac.getBean("beanA", A.class);
		a.helloA();
		
		// B는 빈으로 등록되지 않았다.
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
			ac.getBean("beanB", B.class);			
		});
	}
	
	@Configuration
	static class BasicConfig {
		
		/**
		 * beanA라는 이름으로 A객체 등록
		 * 
		 * @return
		 */
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}
	}
	
	@Slf4j
	static class A {
		public void helloA() {
			log.info("hello A");
		}
	}
	
	@Slf4j
	static class B {
		public void helloA() {
			log.info("hello B");
		}
	}
}
