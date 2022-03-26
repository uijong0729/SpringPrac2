package com.example.demo.order.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.exam.aop.RetryAspect;
import com.example.demo.order.exam.aop.TraceAspect;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Import({TraceAspect.class, RetryAspect.class})
@Slf4j
public class ExamTest {
	@Autowired
	ExamService examService;
	
	@Test
	void test() {
		for(int i = 0 ; i < 5 ; i++){
			examService.request("data" + i);
		}
	} 
}
