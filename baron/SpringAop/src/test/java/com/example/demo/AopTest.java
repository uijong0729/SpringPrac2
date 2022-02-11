package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.OrderRepository;
import com.example.demo.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AopTest {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	void aopInfo() {
		log.info("is Aop Proxy, orderService={}", AopUtils.isAopProxy(orderService));
		log.info("is Aop Proxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
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
