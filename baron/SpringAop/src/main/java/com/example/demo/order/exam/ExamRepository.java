package com.example.demo.order.exam;

import org.springframework.stereotype.Repository;

import com.example.demo.order.exam.annotation.Trace;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class ExamRepository {
	private static int seq = 0;
	
	// 5번에 한번 실패하는 요청
	@Trace
	public String save(String id) {
		seq++;
		log.info("seq={} (seq % 5 == 0)={}", seq, (seq % 5 == 0));
		
		if(seq % 5 == 0) {
			throw new IllegalStateException();
		}
		return "ok => " + id;			
		
	}
}
