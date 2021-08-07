package com.example.ecomapigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    /**
     * application.yml 파일에 의해 작성된 구성정보를 자바코드로 작성할 수 있다.
     *
     * @param builder
     * @return
     */
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/ecom-first-service/**")
                             .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                           .addResponseHeader("first-response", "first-response-header"))
                             .uri("http://localhost:8081"))
                .route(r -> r.path("/ecom-second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
