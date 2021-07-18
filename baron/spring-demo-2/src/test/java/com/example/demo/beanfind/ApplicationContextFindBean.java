package com.example.demo.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;

public class ApplicationContextFindBean {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService bean = ac.getBean("memberService", MemberService.class);
		assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("이름없이 타입으로만 조회(타입의 중복이 없다고 가정)")
	void findBeanByType() {
		MemberService bean = ac.getBean(MemberService.class);
		assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	/**
	 *  구현에 의존한 테스트이기 때문에 좋은 코드는 아님
	 */
	@Test
	@DisplayName("구현체 타입으로 조회")
	@Deprecated
	void findBeanByName2() {
		MemberService bean = ac.getBean("memberService", MemberServiceImpl.class);
		assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 이름으로 조회 실패")
	void findBeanByNameFail() {
		assertThrows(NoSuchBeanDefinitionException.class, () -> {
			MemberService bean = ac.getBean("memberServicer", MemberService.class);
		});
	}
	
}
