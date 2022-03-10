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
@SpringBootTest
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
	}
}
