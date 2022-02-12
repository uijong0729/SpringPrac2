package com.example.demo.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 포인트컷 외부참조
 */
public class Pointcuts {
	/**
	 * 포인트컷을 분리하는 방법
	 * 다른 Aspect에서도 참고하려면 public으로 작성해야 한다.
	 */
	@Pointcut("execution(* com.example.demo.order..*(..))")
	public void allOrder() {} 
	
	// 클래스 이름 패턴이 *Service
	@Pointcut("execution(* *..*Service.*(..))")
	public void allService() {} 
	
	@Pointcut("allOrder() && allService()")
	public void orderAndService() {}
}
