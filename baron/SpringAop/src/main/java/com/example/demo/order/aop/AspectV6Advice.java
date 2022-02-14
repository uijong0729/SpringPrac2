package com.example.demo.order.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * <Advice 종류> 
 *  1. @Around : 메서드 호출 전후에 실행. 가장 강력한 어드바이스
 *  2. @Before : 조인포인트 실행이전에 실행
 *  3. @After Returning : 조인포인트 정상 완료후 실행
 *  4. @After Throwing : 메서드가 예외를 던지는 경우 실행
 *  5. @After
 * 
 * <실행순서>
 *  - 동일한 @Aspect안에서의 실행순서 : @Around @Before
 *  - 동일한 @Aspect안에서의 반환순서 : @AfterThrowing @AfterReturning @After @Around   
 */
@Slf4j
@Aspect
public class AspectV6Advice {

	/**
	 * @param joinPoint : 어드바이스의 첫번째 파라미터는 반드시 ProceedingJoinPoint를 사용해야하는 제약이 있음
	 * @return
	 * @throws Throwable
	 * 
	 *  - 포인트컷 여러개 적용 : &&(and) ||(or) !(not)
	 */
	@Around("com.example.demo.order.aop.Pointcuts.orderAndService()") 
	public Object doTrasaction(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			//@Before
			log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
			
			// proceed()
			//  - 다음 어드바이스나 Target을 호출한다.
			//  - 항상 joinPoint.proceed()를 호출해야한다. 호출하지 않으면 다음의 @Aspect가 실행되지 않는다.
			//  - proceed()를 여러번 호출하면 타겟이 여러번 실행된다.
			Object result = joinPoint.proceed();
			
			//@AfterReturning
			log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
			return result;
		}catch (Exception e) {
			//@AfterThrowing
			log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
			throw e;
		}finally {
			//@After
			log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
		}
	}
	
	/**
	 * @param joinPoint : @Around를 제외한 모든 어드바이스는 JoinPoint를 첫번째 파라미터로 사용할 수 있으며 생략할 수 있다. 
	 * 
	 *  - 기능이 심플하지만 joinPoint.proceed()를 호출하지 않아도 되기 때문에 실수가 적다.
	 */
	@Before("com.example.demo.order.aop.Pointcuts.orderAndService()")
	public void doBefore(JoinPoint joinPoint) {
		log.info("[@Before] {}", joinPoint.getSignature());
	}
	
	/**
	 * @param joinPoint
	 * @param result : 인수명과 returing이 매칭되어야함 / 인수로 Object 타입을 지정했지만 지정한 타입과 매칭되지 않으면 호출되지 않는다.
	 */
	@AfterReturning(value = "com.example.demo.order.aop.Pointcuts.orderAndService()", returning = "result")
	public void doReturn(JoinPoint joinPoint, Object result) {
		log.info("[@AfterReturning] {} {}", joinPoint.getSignature(), result);
	}
	
	/**
	 * @param joinPoint
	 * @param result : 인수명과 returing이 매칭되어야함 / 인수로 Void 타입을 지정했지만 지정한 타입과 매칭되지 않으면 호출되지 않는다.
	 */
	@AfterReturning(value = "com.example.demo.order.aop.Pointcuts.orderAndService()", returning = "result")
	public void doReturn2(JoinPoint joinPoint, Void result) {
		log.info("[@AfterReturning Instanceof Void] {} {}", joinPoint.getSignature(), result);
	}
	
	/**
	 * @param joinPoint
	 * @param ex : 인수명과 throwing이 매칭되어야함
	 */
	@AfterThrowing(value = "com.example.demo.order.aop.Pointcuts.orderAndService()", throwing = "ex")
	public void doThrow(JoinPoint joinPoint, Exception ex) {
		log.info("[@AfterThrowing] {} {}", joinPoint.getSignature(), ex);
	}
	
	/**
	 * @param joinPoint
	 * 
	 * 	- finally의 개념
	 *  - 기능이 심플하지만 joinPoint.proceed()를 호출하지 않아도 되기 때문에 실수가 적다.
	 */
	@After("com.example.demo.order.aop.Pointcuts.orderAndService()")
	public void doAfter(JoinPoint joinPoint) {
		log.info("[@After] {} {}", joinPoint.getSignature());
	}
}
