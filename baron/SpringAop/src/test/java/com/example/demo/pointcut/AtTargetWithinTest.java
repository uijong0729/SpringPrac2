package com.example.demo.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.example.demo.order.aop.member.annotation.ClassAop;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Import({AtTargetWithinTest.Config.class})
@Slf4j
public class AtTargetWithinTest {
	
	@Autowired
	Child child;
	
	@Test
	public void success() {
		log.info("child proxy={}", child.getClass());
		child.childMethod();
		child.parentMethod();
	}
	
	static class Config {
		@Bean 
		public Parent parent() { return new Parent(); }
		@Bean 
		public Child child() { return new Child(); }
		@Bean
		public AtTargetWithinAspect atTargetWithinAspect() {return new AtTargetWithinAspect();}
		
	} 

	static class Parent {
		public void parentMethod() {}
	}
	
	@ClassAop
	static class Child extends Parent {
		public void childMethod() {}
	}
	
	
	/**
	 *	적용시킬 부가로직
	 *
	 *	args, @args, @target은 단독으로 사용해선 안된다. 
	 *  실제 객체 인스턴스에 포인트컷 적용을 판단하기 때문에, 실행시점에 모든 스프링 빈에대해 적용하려하기 때문
	 *
	 */
	@Slf4j
	@Aspect
	static class AtTargetWithinAspect {
		
		/**
		 * @target : 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모타입의 메서드도 적용.
		 */
		@Around("execution(* com.example.demo.pointcut..*(..)) "
				+ "&& @target(com.example.demo.order.aop.member.annotation.ClassAop)")
		public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
			// 조인 포인트 : parentMethod(), childMethod()
			log.info("[@target] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
		
		/**
		 * @within : 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용되지 않음. 
		 */
		@Around("execution(* com.example.demo.pointcut..*(..)) "
				+ "&& @within(com.example.demo.order.aop.member.annotation.ClassAop)")
		public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
			// 조인 포인트 : childMethod()
			log.info("[@within] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}
}
