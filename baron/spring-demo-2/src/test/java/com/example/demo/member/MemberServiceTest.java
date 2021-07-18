package com.example.demo.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.AppConfig;

public class MemberServiceTest {
	
	MemberService ms;
	
	@BeforeEach
	public void beforeEach() {
		this.ms = new AppConfig().memberService();
	}
	
	@Test
	void join() {
		//given
		Member member = new Member(1L, "MemberA", Grade.VIP);
		
		//when
		ms.join(member);
		Member foundMember = ms.findMember(member.id());
		
		//then
		Assertions.assertThat(member).isEqualTo(foundMember);
	}
}
