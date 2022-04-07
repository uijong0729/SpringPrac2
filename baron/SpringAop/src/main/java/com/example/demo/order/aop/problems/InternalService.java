package com.example.demo.order.aop.problems;

import org.springframework.stereotype.Component;

import com.example.demo.order.aop.problems.internalcall.CallServiceV3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InternalService {
	
	public void internal() {
		log.info("call internal method");
	}
}
