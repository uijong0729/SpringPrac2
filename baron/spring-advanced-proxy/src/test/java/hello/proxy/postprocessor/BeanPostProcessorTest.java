package hello.proxy.postprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.postprocessor.BeanBasicTest.A;
import hello.proxy.postprocessor.BeanBasicTest.B;
import hello.proxy.postprocessor.BeanBasicTest.BasicConfig;
import lombok.extern.slf4j.Slf4j;

public class BeanPostProcessorTest {

	@Test
	void basicConfig() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(beanPostProcessorConfig.class);
		
		// A빈을 호출하면 B빈이 조회된다.
		B a = ac.getBean("beanA", B.class);
		a.helloA();
		
		// A빈을 호출하면 B빈이 조회된다.
		Assertions.assertThrows(BeanNotOfRequiredTypeException.class, () -> {
			ac.getBean("beanA", A.class);			
		});
	}
	
	@Configuration
	static class beanPostProcessorConfig {
		
		/**
		 * beanA라는 이름으로 A객체 등록
		 * 
		 * @return
		 */
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}
		
		/**
		 * 빈 후처리기를 사용하기위해 컨테이너에 등록
		 * 
		 * @return
		 */
		@Bean
		public AToBPostProcessor helloPostProcessor() {
			return new AToBPostProcessor();
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
	
	/**
	 * 빈 후처리기
	 *  - 스프링 빈에 등록하기만하면 스프링 컨테이너가 빈 후처리기로 인식하고 동작한다 
	 *  - 빈을 변경/조작할 수 있는 후킹 포인트
	 *  - 빈 객체를 프록시로 교체하는 것도 가능하다
	 *  - 빈 후처리기 자동등록 @PostConstruct 스프링 스스로도 스프링 내부의 기능을 확장하기위해 빈 후처리기를 사용한다.
	 *  - 컴포넌트 스캔을 사용하는 빈까지 모두 프록시를 적용할 수 있다.
	 */
	@Slf4j
	static class AToBPostProcessor implements BeanPostProcessor {
		
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			log.info("beanName={}, bean={}", beanName, bean);
			if (bean instanceof A) {
				return new B();
			}
			return bean;
		}
	}
}
