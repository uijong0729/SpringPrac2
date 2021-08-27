package com.example.ecomapigatewayservice.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 로그인(인증)되어있지 않으면 상태코드를 401로 응답하게하는 필터
 */
@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private Environment env;

    @Autowired
    public AuthorizationHeaderFilter(Environment env) {
        // 부모 클래스에 Config에 쓸 클래스 정보를 알려줘야 한다.
        super(Config.class);
        this.env = env;
    }

    /**
     * 로그인 → 토큰 → 토큰을 가진 클라이언트 → 토큰을 가진 헤더
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(AuthorizationHeaderFilter.Config config) {
        // exchange : 주고받음, 교환 -> exchange에서 Response와 Request정보를 얻을 수 있다.
        // chain : 일련, 이어진 것
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 헤더에 토큰정보가 있는가?
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
            }

            // 인증이 되어있는 상태임이 확인되었을 때
            // Headers는 리스트 형태가 반환되기 때문에 0번째 값을 가져온다.
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            // typically in the Authorization header using the Bearer schema.
            // Authorization: Bearer <token>
            String jwt = authorizationHeader.replace("Bearer", "");

            if(!isJwtValid(jwt)){
                return onError(exchange, "토큰 정보가 유효하지 않습니다", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        });
    }

    // jwt.io
    private boolean isJwtValid(String jwt) {
        String subject = null;
        log.info("jwt string : " + jwt);

        try {
            String surplus = env.getProperty("token.secret");
            log.info("token.secret : " + surplus);

            subject = Jwts.parser().setSigningKey(surplus)
                    // parseClaimsJws : 토큰을 문자열 형태로 파싱 (claim : 요청하다)
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
        }catch (Exception e){
            return false;
        }

        log.info("isJwtValid : " + subject);

        return subject != null && !subject.isEmpty();
    }

    /**
     *
     * @return 스프링 MVC가 아닌 스프링 WebFlux라는 Functional API로 비동기방식 처리객체로 Mono(단일 값)를 사용할 수 있다.
     *
     * Spring WebFlux Functional API
     *  Mono → 단일값 반환
     *  Flux → 다중값 반환
     */
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpsStaus) {
        // Spring 5에서는 서블릿 개념을 사용하지 않는다.
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpsStaus);
        log.error(err);

        // 응답을 Mono 타입으로 전달
        return response.setComplete();
    }

    public static class Config {

    }
}
