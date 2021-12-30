package hello.proxy.pureproxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.pureproxy.app.v1.OrderControllerV1;
import hello.proxy.pureproxy.app.v1.OrderControllerV1Impl;
import hello.proxy.pureproxy.app.v1.OrderRepositoryV1;
import hello.proxy.pureproxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.pureproxy.app.v1.OrderServiceV1;
import hello.proxy.pureproxy.app.v1.OrderServiceV1Impl;

@Configuration
public class AppV1Config {

	@Bean
	public OrderControllerV1 orderControllerV1() {
		return new OrderControllerV1Impl(orderServceV1());
	}

	@Bean
	public OrderServiceV1 orderServceV1() {
		// TODO Auto-generated method stub
		return new OrderServiceV1Impl(orderRepositoryV1());
	}

	@Bean
	public OrderRepositoryV1 orderRepositoryV1() {
		// TODO Auto-generated method stub
		return new OrderRepositoryV1Impl();
	}
}
