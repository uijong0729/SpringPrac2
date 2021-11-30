package app.trace.logtrace;

import org.junit.jupiter.api.Test;

import app.trace.TraceStatus;

class ThreadLocalLogTraceTest {

	LogTrace trace = new ThreadLocalLogTrace();
	
	@Test
	void test() {
		TraceStatus ts1 = trace.begin("start1");
		TraceStatus ts2 = trace.begin("start2");
		trace.end(ts2);
		trace.end(ts1);
	}
	
	@Test
	void testEx() {
		TraceStatus ts1 = trace.begin("start1");
		TraceStatus ts2 = trace.begin("start2");
		trace.end(ts2, new IllegalStateException());
		trace.end(ts1, new IllegalStateException());
	}

}
