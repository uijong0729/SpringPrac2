package com.example.demo.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
	
	// com.example.demo패키지 하위에 모두 적용
	// 표현식은 pointcut expression
	// @Around("execution(* com.example.demo.service..*(..))")
	@Around("execution(* com.example.demo..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		// 메소드 명 콘솔 출력
		System.out.println("START : " + joinPoint.toString());
		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}

}
