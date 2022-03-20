package com.example.demo.order.exam;

import org.springframework.stereotype.Service;

import com.example.demo.order.exam.annotation.Trace;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamService {
	private final ExamRepository examRepository;
	
	@Trace
	public void request(String id) {
		log.info(examRepository.save(id));
	}
}
