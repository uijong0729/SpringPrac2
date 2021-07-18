package com.example.demo.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AppConfig;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;

public class OrderServiceTest {
	MemberService ms;
	OrderService os;
	
	@BeforeEach
	public void beforeEach() {
		// this.ms = new AppConfig().memberService();
		// this.os = new AppConfig().orderService();
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		this.ms = ac.getBean("memberService", MemberService.class);
		this.os = ac.getBean("orderService", OrderService.class);
	}
	
	@Test
	void order() {
		//given
		Member member = new Member(1L, "member", Grade.VIP);

		//when
		ms.join(member);
		Order order = os.createOrder(1L, "mouse", 5000);
		
		//then
		Assertions.assertThat(order.itemPrice()).isEqualTo(5000);
		Assertions.assertThat(order.discountPrice()).isEqualTo(500);
	}
}
