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
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;

public class ApplicationContextFindBeanByType {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
	
	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상있으면 중복오류가 발생한다")
	void findBeanByTypes() {
		assertThrows(NoUniqueBeanDefinitionException.class, () -> {
			// org.springframework.beans.factory.NoUniqueBeanDefinitionException
			MemberRepository bean = ac.getBean(MemberRepository.class);			
		});
	}
	
	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상있으면 빈 이름을 지정하면 된다.")
	void findBeanByName() {
		MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);
		assertThat(bean).isInstanceOf(MemberRepository.class);
		MemberRepository bean2 = ac.getBean("memberRepository2", MemberRepository.class);
		assertThat(bean2).isInstanceOf(MemberRepository.class);
	}
	
	@Test
	@DisplayName("특정 타입을 모두 조회하기")
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
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
	static class SameBeanConfig {
		@Bean 
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}
		
		@Bean 
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
