package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

// JpaRepository 상속으로 스프링 빈 자동등록 
/**
 * Spring JPA : 간단 쿼리 작성 -> 상황에 따라 네이티브 쿼리도 제공해 줌
 * Querydsl : 동적 쿼리 작성
 *
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
	// findById, findAll 등 다양한 메소드를 지원하지만, 일부는 비즈니스마다 다르기 때문에
	// findBy[ ] 규칙으로 작성하면 인터페이스 이름만으로 SpringDataJpa가 대응해준다.
	@Override
	Optional<Member> findByName(String name);
		
}
