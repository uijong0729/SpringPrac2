package com.example.demo.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.AppConfig;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;

public class ApplicationContextFIndExtends {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
	
	@Test
	@DisplayName("부모 타입으로 조회")
	void findBeanByTypeError() {
		assertThrows(NoUniqueBeanDefinitionException.class, () -> {
			// org.springframework.beans.factory.NoUniqueBeanDefinitionException
			ac.getBean(DiscountPolicy.class);			
		});
	}
	
	
	@Test
	@DisplayName("부모 타입으로 조회")
	void findBeanByType() {
		DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("특정 하위타입으로 조회")
	@Deprecated
	void findBeanBySubType() {
		RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모타입으로 모두조회")
	void findBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		for (String key : beansOfType.keySet()) {
			System.out.println(key + ", " + beansOfType.get(key));
		}
		assertThat(beansOfType.size()).isEqualTo(2);
	}
	
	
	/**
	 * @author kroch
	 * 일반적으로 class안에 class를 선언하면 inner class로 작성되지만, static class로 선언하면 별개의 클래스로 선언된다.
	 * 이를 Static nested class라고 하는데, 이 클래스는 독립적으로 분리된 클래스로 간주되어, 
	 * 상위 클래스가 생성되지 않아도, 외부에서 직접 객체를 생성할 수 있다.
	 */
	@Configuration
	static class TestConfig {
		@Bean 
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}
		
		@Bean 
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}
}
