package com.example.demo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.member.MemberRepository;

/**
 * @author kroch
 *	
 *
 */
@Component
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
	private final DiscountPolicy rateDiscountPolicy;
	private final MemberRepository memberRepo;

	/**+
	 * /** +를 입력하고 메소드 위에서 Enter를 누르면 Javadoc 스텁이 작성됩니다.
	 *
	 * @param rateDiscountPolicy
	 * @param memberRepo
	 */
	@Autowired
	public OrderServiceImpl(DiscountPolicy rateDiscountPolicy,
							MemberRepository memberRepo) {
		this.rateDiscountPolicy = rateDiscountPolicy;
		this.memberRepo = memberRepo;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		return new Order(
					memberId, 
					itemName, 
					itemPrice, 
					rateDiscountPolicy.discount(memberRepo.findById(memberId), itemPrice)
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
