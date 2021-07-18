package com.example.demo.discount;

import com.example.demo.member.Member;

public interface DiscountPolicy {
	
	/**
	 * @param member
	 * @param price
	 * @return 할인이 되는 금액
	 */
	int discount(Member member, int price);
}
