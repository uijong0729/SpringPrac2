package com.example.demo.order.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)			// 클래스에 붙일 수 있는 애노테이션임을 지정
@Retention(RetentionPolicy.RUNTIME)	// 동적으로 애노테이션을 읽을 수 있도록 하기위해 런타임으로 지정
public @interface ClassAop {
	
}
