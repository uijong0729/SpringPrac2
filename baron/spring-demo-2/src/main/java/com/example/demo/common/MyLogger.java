package com.example.demo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * 이 빈은 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸된다.
 *  MyLogger 는 Request Scope 이므로 서버를 기동시키는 시점에는 주입할 수 없다.
 *  Controller에서 호출되든, Service에서 호출되든, 해당 HTTP 스코프 이내라면 동일한 인스턴스에 접근된다.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String msg) {
        System.out.printf("[%s] [%s] : %s\n", this.uuid, this.requestURL, msg);
    }

    @PostConstruct
    public void init(){
        this.uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] request scope bean created : %s\n", this.uuid, this.toString());
    }

    @PreDestroy
    public void destroy(){
        System.out.printf("[%s] request scope bean closed : %s\n", this.uuid, this.toString());
    }
}
