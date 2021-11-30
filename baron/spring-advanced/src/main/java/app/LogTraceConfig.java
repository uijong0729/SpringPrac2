package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.trace.logtrace.LogTrace;
import app.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {
	
	@Bean
	public LogTrace logTrace() {
//		return new FieldLogTrace();
		return new ThreadLocalLogTrace();
	}
}
