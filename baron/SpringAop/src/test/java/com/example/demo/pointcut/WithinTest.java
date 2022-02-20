package com.example.demo.pointcut;

import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.order.OrderRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.aop.member.MemberService;
import com.example.demo.order.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WithinTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod; // 메타정보 보관용
	
	@BeforeEach
	public void init() throws NoSuchMethodException, SecurityException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}
	
	//타입이 정확하게 매칭되어야 함
	@Test
	void withinExactPackage() {
		pointcut.setExpression("within(com.example.demo.order.aop.member.MemberServiceImpl)");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
	
	//타입이 정확하게 매칭되어야 함
	@Test
	void withinPackage() {
		pointcut.setExpression("within(com.example.demo.order.aop.member.MemberServiceImpl)");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();
	}
	
}
