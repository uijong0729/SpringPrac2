package app.trace.logtrace;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.trace.TraceStatus;

class FieldLogTraceTest {

	FieldLogTrace trace = new FieldLogTrace();
	
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
