package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

// 스프링 컨테이너와 테스트를 함께 실행한다. -> 통합 테스트 -> 되도록 스프링 컨테이너 없이 테스트 할 수 있도록 훈련하세요
@SpringBootTest
// 테스트 케이스에 이 어노테이션이 있으면 테스트를 실행할때 트랜잭션 Start, 테스트가 끝날때 롤백처리
@Transactional
class SpringDemoApplicationTests {
	
	@Autowired
	private MemberService service;
	@Autowired
	private MemberRepository repo;
	
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		repo.save(member);
		
		Member result = repo.findById(member.getId()).get();

		// jupiter를 사용
		org.junit.jupiter.api.Assertions.assertEquals(result.getId(), member.getId());
		
		// assertj를 사용
		org.assertj.core.api.Assertions.assertThat(member.getId()).isEqualTo(result.getId());
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
		assertThat(member1.getId()).isEqualTo(result.getId());
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
