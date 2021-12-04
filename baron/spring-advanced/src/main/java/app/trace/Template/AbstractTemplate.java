package app.trace.Template;

import app.trace.TraceStatus;
import app.trace.logtrace.LogTrace;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 좋은 설계는 변경이 일어날 때 자연스럽게 드러난다.
 * 템플릿이 없는 상태에서 로직을 변경할 때 모든 클래스의 각 부분을 찾아 고쳐야 할 것이다.
 * 
 * 단일 책임 원칙 (SRP) : 로그부분을 담당하는 전용 클래스
 *
 * @param <T>
 */
@Slf4j
@AllArgsConstructor
public abstract class AbstractTemplate<T> {
	private final LogTrace trace;
	
	public T execute(String message) {
		TraceStatus status = null;
		
		try {
			status = trace.begin(message);
			
			// 로직 호출
			T result = call();
			
			trace.end(status);
			return result;
		} catch (Exception e) {
			trace.end(status, e);
			throw e;
		}
	}

	protected abstract T call();
}
