package com.example.demo.order.aop.problems.internalcall;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class CallLogAspect {

	/**
	 * execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
	 *  ※「?』 는 생략할 수 있다.
	 *  ※ 선언타입 : 클래스
	 */
	@Before("execution(* com.example.demo.order.aop.problems.internalcall..*.*(..))")
	public void doLog(JoinPoint joinPoint) {
		log.info("aop={}", joinPoint.getSignature());
	}
	
}
