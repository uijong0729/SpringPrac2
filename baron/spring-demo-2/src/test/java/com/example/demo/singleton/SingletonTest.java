package com.example.demo.singleton;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberService;

public class SingletonTest {
	
	@Test
	@DisplayName("호출할때마다 객체를 생성하는 구조 => 낭비")
	void NoSingletonContianer() {
		AppConfig ac = new AppConfig();
		MemberService ms = ac.memberService();
		MemberService ms2 = ac.memberService();
		assertNotEquals(ms, ms2);
		assertThat(ms).isNotEqualTo(ms2);
	}
	
	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void SingletonServiceTest() {
		SingletonService instance2 = SingletonService.getInstance();
		SingletonService instance1 = SingletonService.getInstance();
		// equalsTo, equals : 해당 클래스의 equals()를 호출해서 같은지 확인
		// sameAs, same : 해당 인스턴스가 같은 참조를 갖는지 확인
		assertSame(instance1, instance2);
		assertThat(instance2).isSameAs(instance1);
	}
	
	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService ms = ac.getBean("memberService", MemberService.class);
		MemberService ms2 = ac.getBean("memberService", MemberService.class);
		assertSame(ms, ms2);
		assertThat(ms).isSameAs(ms2);
	}
	
}	
