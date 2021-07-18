package com.example.demo.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import com.example.demo.member.Member;

public class AutowiredTest {
	
	@Test
	void autowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}
	
	static class TestBean{
//		예외발생 : UnsatisfiedDependencyException
//		@Autowired		
//		public void setNoBean0(Member nobean) {
//			System.out.println(nobean);
//		}
		
		/** 
		 * 주입이 안돼있으면 호출자체를 안함
		 * @param nobean
		 */
		@Autowired(required = false)		
		public void setNoBean1(Member nobean) {
			System.out.println(nobean);
		}
		
		/**
		 * 주입이 안돼있어도 호출은 되지만, null로 들어온다.
		 * @param nobean
		 */
		@Autowired		
		public void setNoBean2(@Nullable Member nobean) {
			System.out.println(nobean);
		}
		
		/**
		 * 주입이 안돼있으면 Optional.empty로 값이 들어온다.
		 * @param nobean
		 */
		@Autowired		
		public void setNoBean3(Optional<Member> nobean) {
			System.out.println(nobean);
		}
	}
}
