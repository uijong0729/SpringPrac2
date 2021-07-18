package com.example.demo.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.example.demo.member.MemberService;

public class XmlAppContext {
	
	@Test
	void xmlAppContext() {
		// 경로는 src/main/resources부터 탐색한다.
		ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
		MemberService ms = ac.getBean("memberService", MemberService.class);
		assertThat(ms).isInstanceOf(MemberService.class);
	}
}
