package com.example.ecomsecondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 화면을 매핑하지 않는 컨트롤러
@RequestMapping("/ecom-second-service") // 사용자로부터 요청되는 URI 값
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Second Service";
    }
}
