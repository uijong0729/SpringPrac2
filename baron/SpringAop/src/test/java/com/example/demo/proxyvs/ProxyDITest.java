package com.example.demo.proxyvs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.aop.member.MemberService;
import com.example.demo.order.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK동적프록시(인터페이스 기반)
@Import(ProxyDIAspect.class)
public class ProxyDITest {

	@Autowired
	MemberService memberService;	// JDK프록시는 인터페이스를 기반으로 생성된 클래스이므로 주입가능
	
//	@Autowired
//	MemberServiceImpl memberServiceImpl;	// JDK프록시는 memberService를 상속한 클래스이므로 주입시 에러 발생
	
	@Test
	void go() {
		log.info("memberService class={}", memberService.getClass());
//		log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
//		memberServiceImpl.hello("hello");
	}
}
