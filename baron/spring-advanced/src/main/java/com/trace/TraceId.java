package com.trace;

import java.util.UUID;

public class TraceId {
	// 로그추적 식별자
	private String id;
	// 동일 트랜잭션 내 호출의 깊이 
	private int level;
	
	public TraceId() {
		this.id = createId();
		this.level = 0;
	}
	
	private TraceId(String id, int level) {
		this.id = id;
		this.level = level;
	}

	private String createId() {
		// 앞 8 자리만 사용 (거의 중복안되고 어쩌다 중복이되도 문제가 되는 경우는 없음)
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	/**
	 * 트랜잭션 ID는 같지만 깊이가 1 깊어진다.
	 * @return
	 */
	public TraceId createNextId() {
		return new TraceId(id, level + 1);
	}
	
	/**
	 * 트랜잭션 ID는 같지만 깊이가 1 올라온다.
	 * @return
	 */
	public TraceId createPreviousId() {
		return new TraceId(id, level - 1);
	}
	
	public boolean isFirstLevel() {
		return level == 0;
	}

	public String getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}
}
