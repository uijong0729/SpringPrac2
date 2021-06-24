package com.example.demo.repository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;


/**
 * 
 * 스프링 컨테이너 안에 Repository 인스턴스를 생성해서 스프링이 관리한다.
 * @Service, @Controller, @Repository 어노테이션은 @Component의 일종 -> 컴포넌트 스캔
 */
// @Repository
public class InMemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>(); 
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}
