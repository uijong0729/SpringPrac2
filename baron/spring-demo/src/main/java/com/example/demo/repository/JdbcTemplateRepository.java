package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.example.demo.domain.Member;


/**
 * jdbc템플릿을 이용
 *
 */
public class JdbcTemplateRepository implements MemberRepository {

	private final JdbcTemplate jdbcTemplate;

	// 생성자가 1개일땐 Autowired를 생략할 수 있다.
	@Autowired
	public JdbcTemplateRepository(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public Member save(Member member) {
		// insert 쿼리를 짤 필요가 없음
		SimpleJdbcInsert jdbcInsert  = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");		
		
		Map<String, Object> params = new HashMap<>();
		params.put("name", member.getName());
		
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
		member.setId(key.longValue());
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query("select * from member", memberRowMapper());
	}

	private RowMapper<Member> memberRowMapper(){
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
	}
}
