package hello.proxy.config.v1_proxy;

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
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;

/**
 * 구체 클래스 기반 프록시 (비추천)
 * 
 * 테스트
 * http://localhost:8080/v2/request?itemId=333
 * 
 */
@Configuration
public class ConcreteProxyConfig {
	
	@Bean
	public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
		OrderRepositoryV2 repo = new OrderRepositoryV2();
		return new OrderRepositoryConcreteProxy(repo, logTrace);
	}
	
	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
		OrderServiceV2 service = new OrderServiceV2(orderRepositoryV2(logTrace));
		return new OrderServiceConcreteProxy(service, logTrace);
	}
	
	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
		OrderControllerV2 controller = new OrderControllerV2(orderServiceV2(logTrace));
		return new OrderControllerConcreteProxy(controller, logTrace);
	}
}
