package com.example.demo.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * @Order 애노테이션으로 복수의 어드바이스에 순서를 적용 (@Order는 클래스에 유효한 애노테이션) 
 *  1. doTrasaction
 *  2. doLog
 */
@Slf4j
public class AspectV5OrderedAdvice {
	
	@Aspect
	@Order(2)
	public static class LogAspect {
		/**
		 * @param joinPoint
		 * @return
		 * @throws Throwable
		 * @Around execution([aop표현식])
		 * 
		 *  - 포인트컷 시그니쳐 : allOrder()
		 */
		@Around("com.example.demo.order.aop.Pointcuts.allOrder()") 
		public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
			// join point 시그니처 => 메소드 정보
			log.info("[log] {}", joinPoint.getSignature()); 
			return joinPoint.proceed();
		} 
	}
	
	@Aspect
	@Order(1)
	public static class TxAspect {
		/**
		 * @param joinPoint
		 * @return
		 * @throws Throwable
		 * 
		 *  - 포인트컷 여러개 적용 : &&(and) ||(or) !(not)
		 */
		@Around("com.example.demo.order.aop.Pointcuts.orderAndService()") 
		public Object doTrasaction(ProceedingJoinPoint joinPoint) throws Throwable {
			try {
				log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
				Object result = joinPoint.proceed();
				log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
				return result;
			}catch (Exception e) {
				log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
				throw e;
			}finally {
				log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
			}
		}
	}
}
