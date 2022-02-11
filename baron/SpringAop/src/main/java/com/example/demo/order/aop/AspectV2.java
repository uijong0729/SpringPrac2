package com.example.demo.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 * 포인트컷 분리
 */
@Slf4j
@Aspect
public class AspectV2 {

	/**
	 * 포인트컷을 분리하는 방법
	 * 다른 Aspect에서도 참고하려면 public으로 작성해야 한다.
	 */
	@Pointcut("execution(* com.example.demo.order..*(..))")
	private void allOrder() {} 
	
	
	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * @Around execution([aop표현식])
	 * 
	 *  - 포인트컷 시그니쳐 : allOrder()
	 */
	@Around("allOrder()") 
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		// join point 시그니처 => 메소드 정보
		log.info("[log] {}", joinPoint.getSignature()); 
		return joinPoint.proceed();
	} 
}
