package hello.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.trace.logtrace.LogTrace;

/**
 * 실제 객체를 스프링 빈으로 등록하지않고, 프록시를 스프링 빈으로 등록한다.
 * 빈 등록 부분만 바뀌고 원본 코드의 수정은 전혀 이루어지지 않았다.
 * - 단점 : 프록시 클래스를 만들어야하는 귀찮음이 있음
 * 
 * 테스트
 * http://localhost:8080/v1/request?itemId=333
 * 
 */
@Configuration
public class InterfaceProxyConfig {
	
	/**
	 * @param logtrace 빈으로 등록되어 있으면 logtrace는 주입받는다. 
	 * @return
	 */
	@Bean
	public OrderControllerV1 orderController(LogTrace logtrace) {
		OrderControllerV1Impl target = new OrderControllerV1Impl(orderServce(logtrace));
		return new OrderControllerInterfaceProxy(target, logtrace);
	}

	@Bean
	public OrderServiceV1 orderServce(LogTrace logtrace) {
		OrderServiceV1Impl target = new OrderServiceV1Impl(orderRepository(logtrace));
		return new OrderServiceInterfaceProxy(target, logtrace);
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logtrace) {
		OrderRepositoryV1Impl target = new OrderRepositoryV1Impl();
		return new OrderRepositoryInterfaceProxy(target, logtrace);
	}
}
