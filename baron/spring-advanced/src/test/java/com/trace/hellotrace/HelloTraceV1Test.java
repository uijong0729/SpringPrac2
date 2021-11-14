package com.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.trace.TraceStatus;
import app.trace.hellotrace.HelloTraceV1;

class HelloTraceV1Test {

	@Test
	void test() {
		HelloTraceV1 trace = new HelloTraceV1();
		TraceStatus status = trace.begin("hello");
		trace.end(status);
	}

	@Test
	void test2() {
		HelloTraceV1 trace = new HelloTraceV1();
		TraceStatus status = trace.begin("hello");
		trace.end(status, new IllegalAccessException());
	}

}
