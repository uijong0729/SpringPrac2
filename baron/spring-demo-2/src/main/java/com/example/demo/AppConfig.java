package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;

/**
 * 생성자 주입방식
 * XML로 작성하면 자바코드에 손을 댈 필요도 없어지긴 함
 * @Configuration을 포함하지 않아도 Bean은 생성되지만, 싱글톤이 보장되지 않는다. 
 * 스프링 컨테이너에 등록되어 있는 것이 있으면 컨테이너에서 빈을 찾아서 가져오는 구조이기 때문이다. 
 * 
 * @author kroch
 * 
 */
@Configuration
public class AppConfig {
	
	/**
	 * new로 생성후 리턴하기 때문에 싱글톤 맞아? 라는 의문이 들지만 
	 * 바이트코드 조작으로 AppConfig를 확장해서 각각 싱글톤이 되도록 구현을 수정하기 때문에 문제없음
	 * 
	 * @Bean : 스프링 컨테이너에 등록
	 * @return
	 */
	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call memberRepository");
		return new MemoryMemberRepository();
	}
	
	/**
	 * @return
	 * 
	 */
	@Bean
	public MemberService memberService() {
		System.out.println("call memberService");
		return new MemberServiceImpl(memberRepository());
	}
	
	/**
	 * @return
	 * 
	 */
	@Bean
	public OrderService orderService() {
		System.out.println("call orderService");
		return new OrderServiceImpl(discountPolicy(), memberRepository());
	}
	
	/**
	 * @return
	 */
	@Bean
	public DiscountPolicy discountPolicy() {
		//return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
