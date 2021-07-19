package com.example.demo.discount;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.example.demo.annotation.*;

@Component
/*@Qualifier("fixDiscountPolicy")*/
@MainDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{

	private final int DISCOUNT_MOUNT = 1000;
	
	@Override
	public int discount(Member member, int price) {
		return switch(member.grade()) {
			case VIP:
				yield DISCOUNT_MOUNT;
			default:
				yield 0;
		};
	}

}
