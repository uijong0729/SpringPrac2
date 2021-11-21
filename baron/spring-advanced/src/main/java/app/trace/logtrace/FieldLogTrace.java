package app.trace.logtrace;

import java.util.Optional;

import app.trace.TraceId;
import app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

	/**
	 * traceId 를 어딘가에는 들고 있어야하기 떄문에 보관해두고 사용 동기화 이슈 발생
	 */
	private TraceId traceIdHolder;

	@Override
	public TraceStatus begin(String message) {
		syncTraceId();
		TraceId traceId = traceIdHolder;
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
		return new TraceStatus(traceId, startTimeMs, message);
	}

	@Override
	public void end(TraceStatus status) {
		this.end(status, null);

	}

	@Override
	public void end(TraceStatus status, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
		long resultTimeMs = stopTimeMs - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();

		if (e == null) {
			log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),
					status.getMessage(), resultTimeMs);
		} else {
			log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
					status.getMessage(), resultTimeMs, e.toString());
		}

		releaseTraceId();
	}

	/**
	 * 로그를 시작할 때 호출 직전 로그의 TraceId는 파라미터로 전달하는 것이 아니라 traceIdHoler에 저장시킨다.
	 */
	private void syncTraceId() {
		if (traceIdHolder == null) {
			traceIdHolder = new TraceId();
		} else {
			traceIdHolder = traceIdHolder.createNextId();
		}
	}

	/**
	 * 로그를 종료할 때 호출
	 */
	private void releaseTraceId() {
		if (traceIdHolder.isFirstLevel()) {
			traceIdHolder = null;
		} else {
			traceIdHolder.createPreviousId();
		}
	}

	/**
	 * level0 level1 |--> level2 |---|-->
	 * 
	 * @param prefix
	 * @param level
	 * @return
	 */
	private Object addSpace(String prefix, int level) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + prefix : "|---");
		}
		return sb.toString();
	}
}
