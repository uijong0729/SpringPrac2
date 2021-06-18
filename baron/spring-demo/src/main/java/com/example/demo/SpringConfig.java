package com.example.demo;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.InMemoryMemberRepository;
import com.example.demo.repository.JdbcTemplateRepository;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

/**
 * @Service, @Repositroy 어노테이션 대신에,
 * 자바 코드로 직접 스프링 빈 등록하기 (컴포넌트 스캔 X)
 * 
 *	1. 자바 코드로 직접 스프링빈 등록하기
 *		<DI의 3가지 방법 : 필드주입/섹터주입/생성자주입>
 * 		(1) 필드주입 : 필드 접근자가 private이므로 중간에 바꿀 수 있는 방법이 없어서 안좋음
 * 				@Autowired
 * 				private MemberService memberService;
 * 		(2) Setter주입 : set메소드를 통해 주입이 되는 것 (주입을 하려면 public으로 열려있어야함 -> 중간에 바꿀 위험)
 * 				@Autowired
 * 				public setMemberService(MemberService memberService) { ... }
 * 		(3) 생성자 주입 : 생성자를 통해 단 한번 주입
 * 				@Autowired
 *				public MemberController(MemberService memberService) {
 *					this.memberService = memberService;
 *				}
 *
 * 	2. 컴포넌트 스캔 방식에비해 스프링 빈을 직접등록하는 것의 장점
 * 		기존 서비스에 대해 수정할 필요없이 해당 빈만 상황에 따라 다른 구현체로 바꿀 수 있다. 
 * 		빈을 등록하지 않으면 Autowired는 작동하지 않음에 주의 
 * 		
 */
@Configuration
public class SpringConfig {
	
	// private DataSource ds;
	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;		
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		// 개방 폐쇄 원칙 : 확장에는 열려있고 변경에는 닫혀있다.
		// 스프링의  DI를 사용하면 기존코드를 전혀 손대지 않고 설정만으로 구현 클래스를 변경할 수 있다.
		//return new InMemoryMemberRepository();
		// return new JdbcTemplateRepository(ds);
		return new JpaMemberRepository(em);
	}
}
