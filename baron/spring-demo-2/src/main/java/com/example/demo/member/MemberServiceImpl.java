package com.example.demo.member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepo;
	
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}
	
	@Override
	public void join(Member member) {
		memberRepo.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepo.findById(memberId);
	}
	
	
	/**
	 * 싱글톤인지 확인하는 테스트용 메소드 
	 * 
	 * @return
	 */
	public MemberRepository getMemberRepository() {
		return this.memberRepo;
	}
}
