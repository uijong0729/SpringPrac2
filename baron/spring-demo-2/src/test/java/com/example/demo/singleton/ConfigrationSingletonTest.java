package com.example.demo.singleton;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderServiceImpl;

public class ConfigrationSingletonTest {
	
	ApplicationContext ac;
	
	@BeforeEach
	void getAc() {
		ac = new AnnotationConfigApplicationContext(AppConfig.class);
	}
	
	@Test
	void springContainer() {
		MemberServiceImpl bean = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl bean2 = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository bean3 = ac.getBean("memberRepository", MemberRepository.class);
		
		// 모두 같은 인스턴스임을 확인
		assertThat(bean.getMemberRepository()).isSameAs(bean2.getMemberRepo());
		assertThat(bean.getMemberRepository()).isSameAs(bean3);
		assertThat(bean2.getMemberRepo()).isSameAs(bean3);
	}
	
	@Test
	void configrationDepp() {
		AppConfig bean = ac.getBean(AppConfig.class);
		
		// class com.example.demo.AppConfig$$EnhancerBySpringCGLIB$$9e461a9
		// 바이트 코드를 조작해서 AppConfig$$EnhancerBySpringCGLIB 클래스를 작성하고, 이 객체를 스프링 빈에 등록한다.
		System.out.println(bean.getClass());
		
	}
}
