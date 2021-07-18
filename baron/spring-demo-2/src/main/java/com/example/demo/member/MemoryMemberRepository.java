package com.example.demo.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class MemoryMemberRepository implements MemberRepository{
	
	// 동시성 이슈가 있기 때문에 ConcurrentHashMap 사용
	private static Map<Long, Member> store = new ConcurrentHashMap<>();
	
	@Override
	public void save(Member member) {
		store.put(member.id(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}

}
