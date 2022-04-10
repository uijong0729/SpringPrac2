package com.example.demo.proxyvs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class ProxyDIAspect {

	@Before("execution(* com.example.demo..*.*(..))")
	public void doTrace(JoinPoint joinPoint) {
		log.info("[proxyDIAdvice] {}", joinPoint.getSignature());
	}
}
