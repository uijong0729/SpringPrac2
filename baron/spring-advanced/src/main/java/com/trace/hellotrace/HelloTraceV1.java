package com.trace.hellotrace;

import org.springframework.stereotype.Component;

import com.trace.TraceId;
import com.trace.TraceStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HelloTraceV1 {
	
	private static final String START_PREFIX = "-->";
	private static final String COMPLETE_PREFIX = "<--";
	// ex = exception
	private static final String EX_PREFIX = "<X-";
	
	public TraceStatus begin(String message) {
		TraceId traceId = new TraceId();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", 
				traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
		return new TraceStatus(traceId, startTimeMs, message);
	}


	// 정상처리 : end
	public void end(TraceStatus status) {
		this.end(status, null);
	}

	// 예외발생시 처리 : exception
	public void end(TraceStatus status, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
		long resultTimeMs = stopTimeMs - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();
		
		if(e == null) {
			log.info("[{}] {}{} time={}ms", 
					traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
		} else {
			log.info("[{}] {}{} time={}ms ex={}", 
					traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
		}
	}
	
	/**
	 * level0 
	 * level1 |-->
	 * level2 |---|-->
	 * 
	 * @param prefix
	 * @param level
	 * @return
	 */
	private Object addSpace(String prefix, int level) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < level ; i++) {
			sb.append( (i == level - 1) ? "|" + prefix : "|---"  );
		}
		return sb.toString();
	}
}
