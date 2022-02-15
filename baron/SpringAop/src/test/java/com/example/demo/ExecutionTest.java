package com.example.demo;

import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import com.example.demo.order.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

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
}
