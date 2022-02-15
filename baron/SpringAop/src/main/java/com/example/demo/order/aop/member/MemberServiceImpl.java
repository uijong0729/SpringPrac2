package com.example.demo.order.aop.member;

import org.springframework.stereotype.Component;

import com.example.demo.order.aop.member.annotation.ClassAop;
import com.example.demo.order.aop.member.annotation.MethodAop;

@ClassAop
@Component // 빈으로 등록
public class MemberServiceImpl implements MemberService {

	@Override
	@MethodAop("Test Value")
	public String hello(String msg) {
		// TODO Auto-generated method stub
		return "ok";
	}
	
	public String internal(String param) {
		return "ok";
	}

}
