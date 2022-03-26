package com.example.demo.order.exam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.example.demo.order.exam.annotation.Retry;

import lombok.extern.slf4j.Slf4j;

/**
 * 타겟의 로직에서 예외가 발생하면 재시도
 */
@Slf4j
@Aspect
public class RetryAspect {
	
	/**
	 * @param joinPoint		
	 * @param retry			애노테이션을 파라미터로 전달한다. retry.value()를 통해 애노테이션에 저장한 값을 가져올 수 있다.
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(retry)")
	public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
		log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);
		int maxRetry = retry.value();
		Exception exceptionHolder = null;
		
		// 3회까지 재시도
		for (int i = 0; i < maxRetry ; i++) {
			try {
				log.info("[retry] retry cound = {}", i);
				// 로직 실행
				return joinPoint.proceed();
			} catch (Exception e) {
				// 예외가 발생하면 담아 둠
				exceptionHolder = e;
			}
		}
		
		// 3회 재시도 후 예외를 던져버림
		throw exceptionHolder;
	}
	
}
