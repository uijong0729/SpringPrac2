package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloRestController {
	
	// 테스트는 Postman의 request 서비스를 이용
	// https://web.postman.co/workspace/My-Workspace~61fd6a4e-7a16-4d8a-8d00-491c4f7d091f/request/16369243-66058534-6de0-4473-b7e3-bcce78dd6189
	// Collections -> Add Request
	// 로그인 없이는 unauth에러 
	@GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
