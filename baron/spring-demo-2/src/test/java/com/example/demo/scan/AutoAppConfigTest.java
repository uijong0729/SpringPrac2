package com.example.demo.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AutoAppConfig;
import com.example.demo.member.MemberService;

public class AutoAppConfigTest {
	AnnotationConfigApplicationContext ac;
	
	@BeforeEach
	void inject() {
		ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
	}
	
	@Test
	void scan() {
		MemberService bean = ac.getBean(MemberService.class);
		assertThat(bean).isInstanceOf(MemberService.class);
	}
}
