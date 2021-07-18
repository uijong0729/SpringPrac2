package com.example.demo.filter;

import java.lang.annotation.*;

// ElementType이 TYPE이면 클래스에 붙는 애노테이션 
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
	
}
