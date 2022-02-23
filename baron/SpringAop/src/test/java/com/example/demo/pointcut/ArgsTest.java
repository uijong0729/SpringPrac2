package com.example.demo.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import com.example.demo.order.aop.member.MemberServiceImpl;

/**
 * Excution은 파라미터의 타입이 정확하게 매칭되어야한다.
 * args는 부모타입을 허용한다. args는 실제 넘어온 파라미터 객체 인스턴스를 보고 판단한다.
 *
 */
public class ArgsTest {

	Method helloMethod; // 메타정보 보관용
	
	@BeforeEach
	public void init() throws NoSuchMethodException, SecurityException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}
	
	private AspectJExpressionPointcut pointcut(String expression) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		return pointcut;
	}
	
	/**
	 * 	excution() : 메서드의 시그니처로 판단 (정적 판단), 부모타입 인수X
	 *  args() : 런타임에 전달된 인수로 판단 (동적 판단), 부모타입 인수O
	 */
	@Test
	void args() {
		assertThat(pointcut("args(String)")
				.matches(helloMethod, MemberServiceImpl.class))
				.isTrue();
		assertThat(pointcut("args(Object)")
				.matches(helloMethod, MemberServiceImpl.class))
				.isTrue();
		assertThat(pointcut("args()")
				.matches(helloMethod, MemberServiceImpl.class))
				.isFalse();
		assertThat(pointcut("args(..)")
				.matches(helloMethod, MemberServiceImpl.class))
				.isTrue();
		assertThat(pointcut("args(*)")
				.matches(helloMethod, MemberServiceImpl.class))
				.isTrue();
		assertThat(pointcut("args(java.io.Serializable)")
				.matches(helloMethod, MemberServiceImpl.class))
				.isTrue();
	}
	
	/**
	 * 	excution() : 메서드의 시그니처로 판단 (정적 판단), 부모타입 인수X
	 *  args() : 런타임에 전달된 인수로 판단 (동적 판단), 부모타입 인수O
	 */
	@Test
	void excution() {
		assertThat(pointcut("execution(* *(String))")
				.matches(helloMethod, MemberServiceImpl.class))
				.isTrue();
		assertThat(pointcut("execution(* *(Object))")
				.matches(helloMethod, MemberServiceImpl.class))
				.isFalse();
		assertThat(pointcut("execution(* *(java.io.Serializable))")
				.matches(helloMethod, MemberServiceImpl.class))
				.isFalse();
	}
}
