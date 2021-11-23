package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.trace.logtrace.FieldLogTrace;
import app.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {
	
	@Bean
	public LogTrace logTrace() {
		return new FieldLogTrace();
	}
}
