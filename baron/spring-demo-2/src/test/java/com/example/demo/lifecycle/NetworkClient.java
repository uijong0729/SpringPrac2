package com.example.demo.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출" + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect "+ url);
    }

    public void call(String msg){
        System.out.println("call : "+url+ "/ msg : "+ msg);
    }

    public void disconnect(){
        System.out.println("close" + url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("afterPropertiesSet");
        this.connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("destroy");
        disconnect();
    }
}
