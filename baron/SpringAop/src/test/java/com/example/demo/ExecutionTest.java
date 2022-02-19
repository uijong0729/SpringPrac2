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
	
	
	// --------------------------------
	// 타입 매칭
	// --------------------------------
	@Test
	void typeExactMatch1() {
		// MemberServiceImpl의 모든 메서드
		pointcut.setExpression("execution(* com.example.demo.order.aop.member.MemberServiceImpl.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void typeExactMatch2() {
		// MemberService 인터페이스에도 대응하는가? (부모타입, 부모타입에 있는 메서드만 대응가능)
		pointcut.setExpression("execution(* com.example.demo.order.aop.member.MemberService.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void typeExactMatch3() throws NoSuchMethodException, SecurityException {
		// MemberService 인터페이스에도 대응하는가? (부모타입, 부모타입에 있는 메서드만 대응가능)
		// 포인트컷에 부모타입을 지정
		pointcut.setExpression("execution(* com.example.demo.order.aop.member.MemberService.*(..))");
		// 부모타입에 없는 메서드가 포인트 컷에 걸리는지 실험하기위해 부모타입에 없는 메서드의 메타정보를 취득
		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		// 검증
		Assertions.assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
	}
	
	@Test
	void typeExactMatch4() throws NoSuchMethodException, SecurityException {
		pointcut.setExpression("execution(* com.example.demo.order.aop.member.MemberServiceImpl.*(..))");
		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		Assertions.assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
	}
	
	
	// --------------------------------
	// 파라미터 매칭
	// --------------------------------
	@Test
	void argsMatch() throws NoSuchMethodException, SecurityException {
		// 파라미터가 String인 타겟과 매칭
		pointcut.setExpression("execution(* *(String))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void noArgsMatch() throws NoSuchMethodException, SecurityException {
		// 파라미터가 없는 타겟과 매칭
		pointcut.setExpression("execution(* *())");
		// hello() 메서드는 파라미터로 String을 받기 때문에 false
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}
	
	@Test
	void oneArgMatch() throws NoSuchMethodException, SecurityException {
		// 정확히 하나의 파라미터 허용
		pointcut.setExpression("execution(* *(*))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void argsMatchAll() throws NoSuchMethodException, SecurityException {
		// 모든 파라미터 허용
		pointcut.setExpression("execution(* *(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void argsMatchIf() throws NoSuchMethodException, SecurityException {
		// String타입으로 시작하는 모든 파라미터 허용
		pointcut.setExpression("execution(* *(String, ..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	@Test
	void argsMatchIf2() throws NoSuchMethodException, SecurityException {
		// String타입으로 시작하면서 총 2개의 파라미터 허용
		pointcut.setExpression("execution(* *(String, *))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
}
