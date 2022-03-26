package com.example.demo.order.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
	// 재시도 최대한도 (한도가 없으면 셀프 DDOS발생 우려있음)
	int value() default 3;
}
