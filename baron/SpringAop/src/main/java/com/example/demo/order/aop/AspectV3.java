package com.example.demo.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 * 복수의 어드바이스 적용 
 *  1. doLog
 *  2. doTrasaction
 */
@Slf4j
@Aspect
public class AspectV3 {

	/**
	 * 포인트컷을 분리하는 방법
	 * 다른 Aspect에서도 참고하려면 public으로 작성해야 한다.
	 */
	@Pointcut("execution(* com.example.demo.order..*(..))")
	private void allOrder() {} 
	
	// 클래스 이름 패턴이 *Service
	@Pointcut("execution(* *..*Service.*(..))")
	private void allService() {} 
	
	
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
	
	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * 
	 *  - 포인트컷 여러개 적용 : &&(and) ||(or) !(not)
	 */
	@Around("allOrder() && allService()") 
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
