package app.trace.callback;

import app.trace.TraceStatus;
import app.trace.logtrace.LogTrace;

public class TraceTemplate {
	private final LogTrace trace;

	public TraceTemplate(LogTrace trace) {
		super();
		this.trace = trace;
	}
	
	public <T> T execute(String message, TraceCallback<T> callback) {
		TraceStatus status = null;
		
		try {
			status = trace.begin(message);
			
			// 로직 호출
			T result = callback.call();
			
			trace.end(status);
			return result;
		} catch (Exception e) {
			trace.end(status, e);
			throw e;
		}
	}
}
