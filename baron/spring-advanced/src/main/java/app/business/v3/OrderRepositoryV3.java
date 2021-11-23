package app.business.v3;

import org.springframework.stereotype.Repository;

import app.trace.TraceStatus;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
	private final LogTrace trace;

	public void save(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderRepositoryV2.save()");
			
			if (itemId.equals("ex")) {
				throw new IllegalStateException("error");
			}
			sleep(1000);		
			
			trace.end(status);
		} catch (Exception e) {
			// 예외처리 로그도 남겨야 하므로 코드가 더러워진다.
			trace.end(status, e);
			throw e; // 여기서 예외를 먹어버려, 정상흐름으로 동작하므로 예외를 다시 던져줘야한다.
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
