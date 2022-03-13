package com.example.demo.order.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
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
