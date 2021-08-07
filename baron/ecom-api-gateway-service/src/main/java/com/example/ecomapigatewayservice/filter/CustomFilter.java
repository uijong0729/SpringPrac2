package com.example.ecomapigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    // 이 생성자 없으면 에러 남
    public CustomFilter() {
        super(Config.class);
    }

    public static class Config {
        // Put the configuration properties
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // reactive 패키지 주의
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre Filter request id -> {}", request.getId());

            // Mono : 스프링5에서 추가된 비동기방식 WebFlux
            // 참고 https://www.baeldung.com/spring-webflux
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom Post Filter response code -> {}", response.getStatusCode());
            }));
        });
    }
}
