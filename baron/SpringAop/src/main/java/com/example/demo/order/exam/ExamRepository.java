package com.example.demo.order.exam;

import org.springframework.stereotype.Repository;

import com.example.demo.order.exam.annotation.Retry;
import com.example.demo.order.exam.annotation.Trace;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class ExamRepository {
	private static int seq = 0;
	
	/**
	 * 5번에 한번 실패하는 요청
	 * 
	 * @param id
	 * @return
	 */
	@Trace				//해당 어노테이션이 적용된 곳에 로그를 기록
	@Retry(value = 4) 	//value생략시 default값인 3이 적용
	public String save(String id) {
		seq++;
		log.info("seq={} (seq % 5 == 0)={}", seq, (seq % 5 == 0));
		
		if(seq % 5 == 0) {
			throw new IllegalStateException();
		}
		return "ok => " + id;			
		
	}
}
