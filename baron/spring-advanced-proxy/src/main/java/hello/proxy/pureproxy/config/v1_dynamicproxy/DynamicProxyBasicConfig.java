package hello.proxy.pureproxy.config.v1_dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.pureproxy.app.v1.OrderControllerV1;
import hello.proxy.pureproxy.app.v1.OrderControllerV1Impl;
import hello.proxy.pureproxy.app.v1.OrderRepositoryV1;
import hello.proxy.pureproxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.pureproxy.app.v1.OrderServiceV1;
import hello.proxy.pureproxy.app.v1.OrderServiceV1Impl;
import hello.proxy.pureproxy.config.v1_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.pureproxy.trace.logtrace.LogTrace;

/**
 * http://localhost:8080/v1/request?itemId=333
 *
 */
@Configuration
public class DynamicProxyBasicConfig {
	
	@Bean
	public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
		OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

		OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
				OrderRepositoryV1.class.getClassLoader(), 
				new Class[] {OrderRepositoryV1.class}, 					//이 프록시는 이 인터페이스를 기반으로 만들거에요
				new LogTraceBasicHandler(orderRepository, logTrace));	//target이 각각 다르기 때문에 인스턴스를 따로 생성해야한다.
		
		return proxy;
	}
	
	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
		OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
				OrderServiceV1.class.getClassLoader(), 
				new Class[] {OrderServiceV1.class}, 
				new LogTraceBasicHandler(orderService, logTrace));
		return proxy;
	}

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1 controller = new OrderControllerV1Impl(orderServiceV1(logTrace));
		OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
				OrderControllerV1.class.getClassLoader(), 
				new Class[] {OrderControllerV1.class}, 
				new LogTraceBasicHandler(controller, logTrace));
		return proxy;
	}
	
}
