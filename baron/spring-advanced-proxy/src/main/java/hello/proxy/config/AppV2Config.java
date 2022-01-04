package hello.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;

@Configuration
public class AppV2Config {

	@Bean
	public OrderControllerV2 orderControllerV2() {
		return new OrderControllerV2(orderServceV2());
	}

	@Bean
	public OrderServiceV2 orderServceV2() {
		// TODO Auto-generated method stub
		return new OrderServiceV2(orderRepositoryV2());
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2() {
		// TODO Auto-generated method stub
		return new OrderRepositoryV2();
	}
}
