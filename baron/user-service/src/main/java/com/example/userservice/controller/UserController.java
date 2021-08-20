package com.example.userservice.controller;

import com.example.userservice.vo.Greeting;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {
    private Environment env;
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, Greeting greeting) {
        this.env = env;
        this.greeting = greeting;
    }

    @GetMapping("/health_check")
    public String status() {
        log.info("check status");
        return "It's working in user-service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        // application.yml 파일의 설정정보를 가져 옴
        // return env.getProperty("greeting.msg");
        return greeting.getMsg();
    }
}
