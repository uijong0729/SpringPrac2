package com.example.demo.discount;

import org.springframework.stereotype.Component;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

	private final int DISCOUNT_PERCENT = 10;
	
	@Override
	public int discount(Member member, int price) {
		return switch(member.grade()) {
			case VIP:
				yield (int) (price * (DISCOUNT_PERCENT/100.0));
			default:
				yield 0;
		};
	}

}
