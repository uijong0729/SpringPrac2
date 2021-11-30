package app.trace.logtrace;

import app.trace.TraceId;
import app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

	/**
	 * traceId 를 어딘가에는 들고 있어야하기 떄문에 보관해두고 사용 동기화 이슈 발생
	 */
//	private TraceId traceIdHolder;
	private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

	@Override
	public TraceStatus begin(String message) {
		syncTraceId();
		TraceId treaceId = traceIdHolder.get();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", treaceId.getId(), addSpace(START_PREFIX, treaceId.getLevel()), message);
		return new TraceStatus(treaceId, startTimeMs, message);
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

		this.releaseTraceId();
	}

	/**
	 * 로그를 시작할 때 호출 직전 로그의 TraceId는 파라미터로 전달하는 것이 아니라 traceIdHoler에 저장시킨다.
	 */
	private void syncTraceId() {
		TraceId id = traceIdHolder.get();
		if (id == null) {
			this.traceIdHolder.set(new TraceId());
		} else {
			traceIdHolder.set(id.createNextId());
		}
	}

	/**
	 * 로그를 종료할 때 호출
	 */
	private void releaseTraceId() {
		TraceId id = traceIdHolder.get();
		if (id.isFirstLevel()) {
			// 현재 쓰레드의 로컬저장소를 비운다.
			// 		traceIdHolder = null로 지정하면, ThreadLocal포인터는 null이 되겠지만
			// 		그 로컬 필드의 데이터는 누수로 발생한다.
			traceIdHolder.remove();
		} else {
			traceIdHolder.set(id.createPreviousId());
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
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + prefix : "|---");
		}
		return sb.toString();
	}
}
