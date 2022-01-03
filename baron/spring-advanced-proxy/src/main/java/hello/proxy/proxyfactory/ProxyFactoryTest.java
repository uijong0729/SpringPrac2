package hello.proxy.proxyfactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyFactoryTest {
	
	@Test
	@DisplayName("인터페이스가 있으면 JDK동적 프록시 사용")
	void interfaceProxy() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.addAdvice(new TimeAdvice());
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
		log.info("targetClass={}", target);
		log.info("proxyClass={}", proxy);
		
		// 프록시가 적용되어있나 동작시켜보자
		proxy.save();
		
		
		// 프록시 팩토리를 쓸 때만 사용가능
		boolean isProxy = AopUtils.isAopProxy(proxy);
		assertTrue(isProxy);
		
		//ServiceImpl는 ServiceInterface라는 인터페이스가 있기 때문에 JDK동적프록시가 호출된다.
		isProxy = AopUtils.isJdkDynamicProxy(proxy);	
		assertTrue(isProxy);
		
		isProxy = AopUtils.isCglibProxy(proxy);
		assertFalse(isProxy);
	}
	
	@Test
	@DisplayName("구체클래스만 있으면 CGLIB 프록시 사용")
	void concreteProxy() {
		ConcreteService target = new ConcreteService();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.addAdvice(new TimeAdvice());
		ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
		log.info("targetClass={}", target);
		log.info("proxyClass={}", proxy);
		
		// 프록시가 적용되어있나 동작시켜보자
		proxy.call();
		
		
		// 프록시 팩토리를 쓸 때만 사용가능
		boolean isProxy = AopUtils.isAopProxy(proxy);
		assertTrue(isProxy);
		
		isProxy = AopUtils.isJdkDynamicProxy(proxy);	
		assertFalse(isProxy);
		
		//ConcreteService는 인터페이스가 없기 때문에 바이트코드를 조작해서 동적확장 클래스를 만드는 Cglib 프록시가 호출된다.
		isProxy = AopUtils.isCglibProxy(proxy);
		assertTrue(isProxy);
	}
	
	
	@Test
	@DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB기반(클래스확장기반) 프록시 사용")
	void proxyTargetClass() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		// setProxyTargetClass(true) ==> target class를 기반으로 클래스를 만들거야 (CGLIB기반)
		// 스프링 부트는 기본적으로 AOP를 적용할때 true로 설정해서 사용된다.
		proxyFactory.setProxyTargetClass(true);	
		proxyFactory.addAdvice(new TimeAdvice());
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
		log.info("targetClass={}", target);
		log.info("proxyClass={}", proxy);
		proxy.save();
		
		
		// 프록시 팩토리를 쓸 때만 사용가능
		boolean isProxy = AopUtils.isAopProxy(proxy);
		assertTrue(isProxy);
		
		isProxy = AopUtils.isJdkDynamicProxy(proxy);	
		assertFalse(isProxy);
		
		isProxy = AopUtils.isCglibProxy(proxy);
		assertTrue(isProxy);
	}
}
