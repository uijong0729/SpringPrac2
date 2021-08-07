package com.example.ecomfirstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8000/ecom-first-service/welcome

@RestController // 화면을 매핑하지 않는 컨트롤러
@RequestMapping("/ecom-first-service") // 사용자로부터 요청되는 URI 값
@Slf4j
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){
        // 롬복의 기능 : @Slf4j로 log변수를 사용할 수 있다.
        log.info(header);
        return "Hello world in First Service : " + header;
    }

    @GetMapping("/check")
    public String check(){
        return "First Service Check";
    }
}
