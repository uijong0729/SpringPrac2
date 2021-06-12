package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	// #####################################
	// 스프링으로 정적 컨텐츠 개발
	// #####################################
	// 정적 컨텐츠 
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("data","hello!!");
		return "hello";
	}
	
	// #####################################
	// 스프링으로 MVC 개발
	// #####################################
	// 스프링으로 MVC 개발 -> 템플릿 엔진 이용
	@GetMapping("/hello-mvc")
	public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	// #####################################
	// 스프링으로 API 개발 -> 평문
	// #####################################
	@GetMapping("/hello-api")		
	@ResponseBody			// response시, 데이터의 body를 내가 직접 넣어주겠다 (html형식으로 return하지 않을경우)
	public String helloApi(@RequestParam(name = "name", required = false) String name) {
		return "hello " + name;
	}
	
	// #####################################
	// 스프링으로 API 개발 -> 객체로 Return하면 JSON파일을 리턴한다.
	// #####################################
	// return -> Record 
	@GetMapping("/hello-api-record")	
	@ResponseBody			// 객체를 반환할때는 기본으로 JSON을 반환함(설정에 따라 xml도 가능)
	public Hello helloApiRecord(@RequestParam(name = "name", required = false) String name) {
		Hello hello = new Hello("my name");
		return hello;
	}

	// record를 쓰기전에는 getter/setter는 자바빈 규약으로 class를 만들고 준비해두는게 맞음
	record Hello(String name) {}
	
}
