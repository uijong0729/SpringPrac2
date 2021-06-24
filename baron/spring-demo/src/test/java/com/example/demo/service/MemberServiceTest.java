package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Member;
import com.example.demo.repository.InMemoryMemberRepository;

public class MemberServiceTest {
	
	// 테스트는 과감하게 한글로 바꾸셔도 됩니다.
	InMemoryMemberRepository repo;
	MemberService ms;
	
	@BeforeEach
	public void beforeEach() {
		repo = new InMemoryMemberRepository();
		// 서비스를 이용하기 위해선 리포지토리에 의존하기 때문에 처음부터 생성자에 넣어줌
		ms = new MemberService(repo);
	}
	
	// 각 테스트가 끝날때마다 레포지토리를 깨끗하게 지워야함
	@AfterEach
	public void afterEach() {
		repo.clearStore();
	}
	
	/**
	 * 테스트의 기본 패턴 
	 * given -> when -> then
	 * 
	 */
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("hello");
		
		//when
		Long saveId = ms.join(member);
		
		//then
		Member findMember = ms.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	void 중복회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		ms.join(member1);
		// 예외가 발생해야 정상
		IllegalStateException e = assertThrows(IllegalStateException.class, ()-> { 
			ms.join(member2); 
		});
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원이름 : " + member1.getName());
		
//		try {
//			ms.join(member1);
//			// 예외가 발생해야 정상
//			fail();
//		}catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원이름 : spring");
//		}
	}
	
	@Test
	void 여러명_멤버찾기() {
		
	}
	
	@Test
	void 멤버찾기() {
		
	}
}
