package com.example.demo.proxyvs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.example.demo.order.aop.member.MemberService;
import com.example.demo.order.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCastingTest {

	/**
	 * MemberServiceImpl	: 구체 클래스 (MemberService를 구현)
	 * MemberService		: 인터페이스
	 */
	@Test
	void jdkProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(false); // jdk 동적 프록시
		
		// 프록시를 인터페이스로 캐스팅 → 성공
		MemberService proxy = (MemberService) proxyFactory.getProxy();
		
		// JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패
		Assertions.assertThrows(ClassCastException.class, () -> {
			// JDK 동적프록시는 MemberService기반으로 프록시를 만들기 때문에
			// MemberServiceImpl의 존재를 알지 못한다
			MemberServiceImpl casting = (MemberServiceImpl) proxy;			
		});
	}
	
	/**
	 * MemberServiceImpl	: 구체 클래스 (MemberService를 구현)
	 * MemberService		: 인터페이스
	 */
	@Test
	void cglibProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true); // jdk 동적 프록시
		
		// 프록시를 인터페이스로 캐스팅 → 성공
		MemberService proxy = (MemberService) proxyFactory.getProxy();
		
		// CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
		// CGLIB는 MemberServiceImpl를 상속받아 프록시를 생성하기 때문
		MemberServiceImpl casting = (MemberServiceImpl) proxy;
	}
}
