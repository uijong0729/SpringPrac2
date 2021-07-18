package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.member.*;

public class MemberApp {
	
	public static void main(String[] args) {
		// AppConfig에서 추상 클래스 발행 (구현체 포함) 
		// AppConfig appConfig = new AppConfig();
		// 주입
		// MemberService ms = appConfig.memberService();
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService ms = ac.getBean("memberService", MemberService.class);
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		ms.join(member);
		Member result = ms.findMember(member.id());
		
		System.out.println(member.name());
		System.out.println(result.name());
	}

}
