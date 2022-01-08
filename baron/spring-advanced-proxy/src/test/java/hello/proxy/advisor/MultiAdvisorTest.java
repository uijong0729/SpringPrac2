package hello.proxy.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

public class MultiAdvisorTest {
	
	/**
	 * Client -> proxy2(advisor2) -> proxy1(advisor1) -> target
	 * 
	 * ■문제점
	 *  - 프록시를 프록시 개수만큼 생성해야한다.
	 *  - 하나의 프록시에 여러 어드바이저를 적용할 수 있으므로 multiAdvisorTest1()는 잘못된 예
	 */
	//@Test
	@DisplayName("여러 프록시")
	void multiAdvisorTest1() {
		ServiceInterface target = new ServiceImpl();
		
		// 프록시 1 생성
		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
		proxyFactory1.addAdvisor(advisor1);
		ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();
		
		// 프록시 2 생성 
		ProxyFactory proxyFactory2 = new ProxyFactory(proxy1); // 인수로 proxy1이 오는 것에 주의
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
		proxyFactory2.addAdvisor(advisor2);
		ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();
		
		// 실행
		proxy2.save();
	}
	
	/**
	 * Client -> proxy -> (advisor2) -> (advisor1) -> target
	 * 스프링의 AOP는 target마다 하나의 프록시만 생성한다. 
	 */
	@Test
	@DisplayName("하나의 프록시, 여러 어드바이저")
	void multiAdvisorTest2() {
		// target
		ServiceInterface target = new ServiceImpl();
		
		// 여러 어드바이저
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
		
		// 프록시 1 생성
		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		
		// add되는 순서대로 호출되므로 advisor2를 먼저 호출
		proxyFactory1.addAdvisor(advisor2);
		proxyFactory1.addAdvisor(advisor1);
		
		// 실행
		ServiceInterface proxy = (ServiceInterface) proxyFactory1.getProxy();
		proxy.save();
	}
	
	@Slf4j
	static class Advice1 implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			// TODO Auto-generated method stub
			log.info("advice1 호출");
			return invocation.proceed();
		}
	}
	
	@Slf4j
	static class Advice2 implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			// TODO Auto-generated method stub
			log.info("advice2 호출");
			return invocation.proceed();
		}
	}
}
