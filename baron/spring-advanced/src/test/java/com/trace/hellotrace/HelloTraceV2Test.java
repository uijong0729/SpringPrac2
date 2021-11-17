package com.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.trace.TraceStatus;
import app.trace.hellotrace.HelloTraceV2;

class HelloTraceV2Test {

	@Test
	void test() {
		HelloTraceV2 trace = new HelloTraceV2();
		
		TraceStatus status1 = trace.begin("hello");
		TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
		
		trace.end(status2);
		trace.end(status1);
	}

	@Test
	void test2() {
		HelloTraceV2 trace = new HelloTraceV2();
		
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
		
		trace.end(status2, new IllegalAccessException());
		trace.end(status1, new IllegalAccessException());
	}

}
