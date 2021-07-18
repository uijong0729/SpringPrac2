package com.example.demo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemoryMemberRepository;

/**
 * @author kroch
 *	
 *
 */
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	/**
	 * 	<문제점1>
	 *	추상 인터페이스 의존에는 O : DisCountPolicy
	 *	그러나 구현클래스에도 의존 중 (new로 생성해줘야하는 의존 -> 소스코드 변경발생) : FixDiscountPolicy, RateDiscountPolicy
	 *	인터페이스에만 의존하도록 변경이 필요
	 */
	// private final MemberRepository memberRepo = new MemoryMemberRepository();
	// private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	// private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	/**
	 * 	<문제점1>을 개선하는 방법
	 * 	new RateDiscountPolicy() 부분을 지운다
	 *  -> 구현객체를 누군가 대신 생성하고 주입해주어야 한다.
	 */
	private final DiscountPolicy discountPolicy;
	private final MemberRepository memberRepo;

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		return new Order(
					memberId, 
					itemName, 
					itemPrice, 
					discountPolicy.discount(memberRepo.findById(memberId), itemPrice)
				);
	}

	/**
	 * 싱글톤인지 확인하는 테스트용 메소드
	 * 
	 * @return
	 */
	public MemberRepository getMemberRepo() {
		return memberRepo;
	}

}
