package com.example.demo.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.demo.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Import(BeanTest.BeanAspect.class)
@Slf4j
public class BeanTest {
	
	@Autowired
	OrderService orderService;
	
	@Test
	void success() {
		orderService.orderItem("item A");
	}
	
	/**
	 * 빈의 이름이 절대로 바뀌지 않는다고 가정한다면 효과적임
	 */
	@Aspect
	static class BeanAspect {
		@Around("bean(orderService) || bean(*Repository)")
		public Object doLog(ProceedingJoinPoint joinpoint) throws Throwable {
			log.info("[start bean] {}", joinpoint.getSignature());
			Object result = joinpoint.proceed();
			log.info("[end bean] {}", joinpoint.getSignature());
			return result;
		}
		
	}

}
