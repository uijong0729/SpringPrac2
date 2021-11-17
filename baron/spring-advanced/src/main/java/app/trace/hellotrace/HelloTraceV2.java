package app.trace.hellotrace;

import org.springframework.stereotype.Component;

import app.trace.TraceId;
import app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그를 처음 시작할 때는 begin()을 호출하고 처음이 아닐 때에는 beginSync()를 호출해야한다.
 * 그러나 TraceId를 파라미터로 계속 넘기는 방식은 좀 불편
 * 
 * @author kroch
 */
@Slf4j
@Component
public class HelloTraceV2 {
	
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

	/**
	 * 트랜잭션 ID는 기존과 같이 유지
	 * 메서드 호출 깊이를 표현하는 level은 +1
	 * 
	 * @param beforeTraceId
	 * @param message
	 * @return
	 */
	public TraceStatus beginSync(TraceId beforeTraceId, String message) {
		TraceId traceId = beforeTraceId.createNextId();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", 
				traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
		return new TraceStatus(traceId, startTimeMs, message);
	}
	
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
