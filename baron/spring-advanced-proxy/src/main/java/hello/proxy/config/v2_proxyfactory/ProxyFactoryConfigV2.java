package hello.proxy.config.v2_proxyfactory;

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
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxyfactory.advice.LogTraceAdvice;
import hello.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

/**
 * 구체 클래스만 있는 클레스에 대한 프록시 적용(V2)
 *
 */
@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {
	
	@Bean
	public OrderRepositoryV2 orderRespositoryV2(LogTrace logTrace) {
		// TODO Auto-generated method stub
		OrderRepositoryV2 target = new OrderRepositoryV2();
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvisor(getAdvisor(logTrace));
		
		OrderRepositoryV2 result = (OrderRepositoryV2) factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", target.getClass(), result.getClass());
		return result;
	} 
	
	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logtrace) {
		OrderServiceV2 target = new OrderServiceV2(orderRespositoryV2(logtrace)); // 생성자 주입에도 프록시로 들어간다.
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvisor(getAdvisor(logtrace));
		
		OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", target.getClass(), proxy.getClass());
		return proxy;
	}
	
	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logtrace) {
		OrderControllerV2 target = new OrderControllerV2(orderServiceV2(logtrace)); // 생성자 주입에도 프록시로 들어간다.
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvisor(getAdvisor(logtrace));
		
		OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();
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
