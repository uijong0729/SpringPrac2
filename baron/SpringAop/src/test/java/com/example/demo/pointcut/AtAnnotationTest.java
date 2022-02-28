package com.example.demo.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.aop.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Import(AtAnnotationTest.AtAnnotationAspect.class)
@Slf4j
public class AtAnnotationTest {
	@Autowired
	MemberService memberService;
	
	@Test
	public void success() {
		log.info("memberService proxy={}", memberService.getClass());
		memberService.hello("hello A");
	}
	
	@Slf4j
	@Aspect
	static class AtAnnotationAspect {
		
		/**
		 * @param joinPoint
		 * @return
		 * @throws Throwable
		 * 
		 * 특정 메서드에 특정 애노테이션을 걸어놓고 그 어노테이션이 걸린 메서드를 조인포인트로 함
		 */
		@Around("@annotation(com.example.demo.order.aop.member.annotation.MethodAop)")
		public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("@annotaion {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}
}
