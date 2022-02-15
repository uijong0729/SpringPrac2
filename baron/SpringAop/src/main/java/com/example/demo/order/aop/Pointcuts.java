package com.example.demo.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 포인트컷 외부참조
 * 	1. 포인트컷 지시자
 * 		- execution	:	메소드 실행 조인 포인트를 매칭한다.
 * 		- within	:	특정 타입 내의 조인 포인트를 매칭한다.
 * 		- args		:	인자가 주어진 타입의 인스턴스인 조인 포인트
 * 		- annotation:	메서드가 주어진 애노테이션을 가지고있는 조인 포인트
 * 		- bean 		:	스프링 빈의 이름으로 포인트 컷을 지정한다.
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
