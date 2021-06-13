package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Member;
import com.example.demo.repository.InMemoryMemberRepository;
import com.example.demo.repository.MemberRepository;

public class MemberService {
	private final MemberRepository repo; 
	
	public MemberService(MemberRepository repo) {
		this.repo = repo;
		
	}
	
	/**
	 * 회원 가입 
	 * 
	 * 로직 : 같은 이름이 있는 회원은 안된다
	 * 
	 * @param member
	 * @return
	 */
	public Long join(Member member) {
		repo.findByName(member.getName()).ifPresent(exsist -> {
			throw new IllegalStateException("이미 존재하는 회원이름 : " + exsist.getName());
		});
		
		repo.save(member);
		return member.getId();
	}
	
	/**
	 * 전체 회원 조회
	 * 
	 * 서비스의 메소드는 비즈니스에 가까운 메소드명으로 정해야함
	 * 
	 * @return
	 */
	public List<Member> findMembers(){
		return repo.findAll();
	}
	
	/**
	 * 개인 회원 조회
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Member> findOne(Long id){
		return repo.findById(id);
	}
}
