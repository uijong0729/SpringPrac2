package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "password", length = 512)
	private String password;
	
	@Column(name = "activated", length = 1)
	private int activated;
	
	// 다 대 다 관계
	@ManyToMany
	// 조인으로 새로운 테이블 생성
	@JoinTable(
				name = "user_authority",
				joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
				inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
			)
	private Set<Authority> authrities;

	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int isActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public Set<Authority> getAuthrities() {
		return authrities;
	}

	public void setAuthrities(Set<Authority> authrities) {
		this.authrities = authrities;
	}
	
	
}
