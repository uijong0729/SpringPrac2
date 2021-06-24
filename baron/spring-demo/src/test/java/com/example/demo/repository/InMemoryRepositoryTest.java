package com.example.demo.repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

// 순서 상관없이 모든 메소드가 실행되기 때문에 테스트간 의존관계가 없어야 한다.
class InMemoryRepositoryTest {
	
	InMemoryMemberRepository repo = new InMemoryMemberRepository();

	// 각 테스트가 끝날때마다 레포지토리를 깨끗하게 지워야함
	@AfterEach
	public void afterEach() {
		repo.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		repo.save(member);
		
		Member result = repo.findById(member.getId()).get();

		// jupiter를 사용
		org.junit.jupiter.api.Assertions.assertEquals(result, member);
		
		// assertj를 사용
		org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repo.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repo.save(member2);
		
		Member result = repo.findByName("spring1").get();
		assertThat(member1).isEqualTo(result);
	}
	
	@Test
	public void findByName2() {
		Member member1 = new Member();
		member1.setName("spring1");
		repo.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repo.save(member2);
		
		Member result = repo.findByName("spring1").get();
		assertThat(member2).isNotEqualTo(result);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repo.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repo.save(member2);
		
		List<Member> result = repo.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
	
}
