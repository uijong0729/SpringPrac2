package com.example.demo.order.aop.problems.internalcall;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.aop.problems.InternalService;

import lombok.extern.slf4j.Slf4j;

@Import(CallLogAspect.class)
@SpringBootTest
@Slf4j
class CallServiceV3Test {

	/**
	 * 주입되는건 프록시 객체임
	 */
	@Autowired
	CallServiceV3 service; 
	
	@Autowired
	InternalService internalService;
	
	@Test
	void testExternal() {
		log.info("[test external]target={}", service.getClass());	//프록시 객체임을 확인
		service.external();
	}

	@Test
	void testInternal() {
		log.info("[test internal]target={}", service.getClass());	//프록시 객체임을 확인
		internalService.internal();
	}
}
