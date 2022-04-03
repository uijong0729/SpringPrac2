package com.example.demo.order.aop.problems.internalcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CallServiceV1 {

	/**
	 * 프록시가 들어오게 됨
	 */
	private CallServiceV1 callServiceV1;
	
	/**
	 * @param callServiceV1
	 * 
	 * 생성자 주입이 아닌 setter주입
	 */
	@Autowired
	public void setCallServiceV1(CallServiceV1 callServiceV1) {
		log.info("주입된 클래스 : {}",callServiceV1.getClass());
		this.callServiceV1 = callServiceV1;
	}
	
	public void external() {
		log.info("call external method");
		// internal 메서드 호출
		callServiceV1.internal();
	}
	
	public void internal() {
		log.info("call internal method");
	}
}
