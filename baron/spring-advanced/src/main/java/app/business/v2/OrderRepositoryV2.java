package app.business.v2;

import org.springframework.stereotype.Repository;

import app.trace.TraceId;
import app.trace.TraceStatus;
import app.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
	private final HelloTraceV2 trace;

	public void save(TraceId traceId, String itemId) {
		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderRepositoryV2.save()");
			
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
