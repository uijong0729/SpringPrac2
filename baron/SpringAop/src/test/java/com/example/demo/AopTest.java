package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.OrderRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.aop.AspectV1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
//	@Aspect를 사용하려면 스프링 bean으로 등록해야 하지만, @Import로 스프링 빈도 등록할 수 있다.
@Import(AspectV1.class)	
public class AopTest {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	void aopInfo() {
		log.info("===TEST=== isAopProxy?, orderService={}", AopUtils.isAopProxy(orderService));
		log.info("===TEST=== isAopProxy?, orderRepository={}", AopUtils.isAopProxy(orderRepository));
	}
	
	@Test
	void success() {
		orderService.orderItem("item A");
	}
	
	@Test
	void exception() {
		Assertions.assertThatThrownBy(() -> {
			orderService.orderItem("ex");
		}).isInstanceOf(IllegalStateException.class);
	}
}
