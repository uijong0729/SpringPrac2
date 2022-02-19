package com.example.demo;

import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import com.example.demo.order.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

/*
 * execution(접근제어자? 변환타입 선언타입?메서드이름(파라미터) 예외?)
 * ※ [?]는 생략할 수 있다.
 */
@Slf4j
public class ExecutionTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod; // 메타정보 보관용
	
	@BeforeEach
	public void init() throws NoSuchMethodException, SecurityException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}
	
	@Test
	void printMethod() {
		// 접근지정자 패키지.클래스.메소드(인수)
		// public java.lang.String com.example.demo.order.aop.member.MemberServiceImpl.hello(java.lang.String)
		log.info("helloMethod={}", this.helloMethod);
	}
	
	@Test
	void executionMatch() {
		pointcut.setExpression("execution(public String com.example.demo.order.aop.member.MemberServiceImpl.hello(String))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void executionMatch2() {
		// 가장 많이 생략한 포인트 컷
		//  - 파라미터 (..) : 파라미터 타입과 파라미터 수가 뭐가오든 상관없다는 뜻 
		pointcut.setExpression("execution(* *(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	// --------------------------------
	// 메소드명으로 매칭
	// --------------------------------
	@Test
	void nameMatch() {
		// 가장 많이 생략한 포인트 컷
		//  - 파라미터 (..) : 파라미터 타입과 파라미터 수가 뭐가오든 상관없다는 뜻 
		pointcut.setExpression("execution(* hello(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void namePatternMatch1() {
		pointcut.setExpression("execution(* hel*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void namePatternMatch2() {
		pointcut.setExpression("execution(* *ell*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	
	// --------------------------------
	// 패키지이름 매칭
	// --------------------------------
	@Test
	void packageMatch1() {
		pointcut.setExpression("execution(* com.example.demo.order.aop.member.MemberServiceImpl.hello(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void packageMatch2() {
		pointcut.setExpression("execution(* com.example.demo.order.aop.member.*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void packageMatchSubPackFail() {
		// 하위패키지는 포함되지 않는 예
		// .  : 해당 위치의 패키지
		// .. : 해당 위치의 패키지와 그 하위 패키지도 포함
		pointcut.setExpression("execution(* com.example.demo.*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}
	
	@Test
	void packageMatchSubPackSucc() {
		// 하위패키지도 포함시키는 예
		// .  : 해당 위치의 패키지
		// .. : 해당 위치의 패키지와 그 하위 패키지도 포함
		pointcut.setExpression("execution(* com.example.demo..*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
}
