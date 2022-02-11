package com.example.demo.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV1 {

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * @Around execution([aop표현식])
	 */
	@Around("execution(* com.example.demo.order..*(..))")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		// join point 시그니처 => 메소드 정보
		log.info("[log] {}", joinPoint.getSignature()); 
		return joinPoint.proceed();
	} 
}
