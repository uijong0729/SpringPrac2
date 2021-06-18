package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Member;

public class JpaMemberRepository implements MemberRepository{
	
	//JPA를 쓰려면 EntityManager를 주입받아야 한다.
	private final EntityManager em;
	
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		// insert쿼리 자동생성
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList()
				.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// JPQL : 객체를 대상으로 쿼리를 날리는 것
		return  em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
}
