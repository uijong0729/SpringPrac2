package com.example.demo.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.aop.member.MemberService;
import com.example.demo.pointcut.ParameterTest.ParameterAspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// 프록시 생성시 jdk동적프록시를 기본으로 사용 (인터페이스가 없으면 CGLIB사용)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false")
//프록시 생성시 CGLIB를 기본으로 사용
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
@Import(ThisTargetTest.ThisTargetAspect.class)
public class ThisTargetTest {
	@Autowired
	MemberService memberService;
	
	/**
	 * 프록시가 잘 적용되어있나 확인
	 */
	@Test
	public void success() {
		log.info("memberService proxy={}", memberService.getClass());
		memberService.hello("hello A");
	}
	
	@Slf4j
	@Aspect
	static class ThisTargetAspect {
		
		// 부모타입 허용
		@Around("this(com.example.demo.order.aop.member.MemberService)")
		public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-interface] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
		
		// 부모타입 허용
		@Around("target(com.example.demo.order.aop.member.MemberService)")
		public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-interface] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
		
		// 구체클래스 대상 (JDK동적프록시의 경우는 부모타입(MemberService)은 타겟외)
		@Around("this(com.example.demo.order.aop.member.MemberServiceImpl)")
		public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-Impl] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
		
		// 구체클래스 대상
		@Around("target(com.example.demo.order.aop.member.MemberServiceImpl)")
		public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-Impl] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}
}
