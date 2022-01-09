package hello.proxy.config.v1_proxyfactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v1_proxyfactory.advice.LogTraceAdvice;
import hello.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

/**
 * 인터페이스가 있을때의 프록시 구현 (V1)
 * 
 * 프록시 직접 구현의 단점 : 너무 많은 설정 + 컴포넌트 스캔이 적용된 클래스에는 임의 적용불가
 *
 */
@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

	@Bean
	public OrderRepositoryV1 OrderRespositoryV1(LogTrace logTrace) {
		// TODO Auto-generated method stub
		OrderRepositoryV1 target = new OrderRepositoryV1Impl();
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvisor(getAdvisor(logTrace));
		
		OrderRepositoryV1 result = (OrderRepositoryV1) factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", target.getClass(), result.getClass());
		return result;
	} 
	
	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logtrace) {
		OrderServiceV1 target = new OrderServiceV1Impl(OrderRespositoryV1(logtrace)); // 생성자 주입에도 프록시로 들어간다.
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvisor(getAdvisor(logtrace));
		
		OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", target.getClass(), proxy.getClass());
		return proxy;
	}
	
	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logtrace) {
		OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(logtrace)); // 생성자 주입에도 프록시로 들어간다.
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvisor(getAdvisor(logtrace));
		
		OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", target.getClass(), proxy.getClass());
		return proxy;
	}
	
	
	private Advisor getAdvisor(LogTrace logTrace) {
		// Point-Cut
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*"); // noLog()는 필터링
		
		// advice
		//  : 인터페이스 MethodInterceptor를 구현한 클래스 
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		
		// advisor
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
