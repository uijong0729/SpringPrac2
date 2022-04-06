package com.example.demo.order.aop.problems.internalcall;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CallServiceV2 {

	private final ObjectProvider<CallServiceV2> CallServiceProvider;
	
	public void external() {
		log.info("call external method");
		CallServiceV2 service = CallServiceProvider.getObject();
		service.internal();
	}
	
	public void internal() {
		log.info("call internal method");
	}
}
