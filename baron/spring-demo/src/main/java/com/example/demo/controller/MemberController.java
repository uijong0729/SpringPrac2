package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.MemberService;

/**
 * 스프링 컨테이너 안에 Controller 인스턴스를 생성해서 스프링이 관리한다.
 * @Service, @Controller, @Repository 어노테이션은 @Component의 일종 -> 컴포넌트 스캔
 */
@Controller
public class MemberController {
	
	/**
	 * 여러 컨트롤러들이 서비스를 사용할 수 있지만, 인스턴스를 여러개 만들필요가 없다. 
	 * Autowired : 스프링 컨테이너에서 인스턴스를 가져옴
	 */
	private final MemberService memberService;
	
	// Autowired : 스프링 컨테이너에서 인스턴스를 가져옴
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
}
