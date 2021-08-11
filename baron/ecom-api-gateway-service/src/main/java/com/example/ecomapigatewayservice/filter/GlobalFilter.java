package com.example.ecomapigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    // 이 생성자 없으면 에러 남
    public GlobalFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        // Put the configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // reactive 패키지 주의
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("Global Filter base message -> {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("Global Pre Filter request id -> {}", request.getId());
            }

            // Mono : 스프링5에서 추가된 비동기방식 WebFlux
            // 참고 https://www.baeldung.com/spring-webflux
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("Global Post Filter response code -> {}", response.getStatusCode());
                }
            }));
        });
    }
}
