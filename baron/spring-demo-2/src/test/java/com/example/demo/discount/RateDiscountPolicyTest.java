package com.example.demo.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;

public class RateDiscountPolicyTest {

	RateDiscountPolicy policy = new RateDiscountPolicy();
	
	@Test
	@DisplayName("VIP는 10%할인이 적용되어야 한다")
	void vip_0() {
		//given
		Member mem = new Member(1L, "vip", Grade.VIP);
		
		//when
		int adjustedPrice = policy.discount(mem, 10000); 
		
		//then
		Assertions.assertThat(adjustedPrice).isEqualTo(1000);
	}
	
	@Test
	@DisplayName("BASIC은 할인이 적용되어선 안된다")
	void basic_0() {
		//given
		Member mem = new Member(2L, "vip", Grade.BASIC);
		
		//when
		int adjustedPrice = policy.discount(mem, 10000); 
		
		//then
		Assertions.assertThat(adjustedPrice).isEqualTo(0);
	}
}
