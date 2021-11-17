package app.business.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.trace.TraceId;
import app.trace.TraceStatus;
import app.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;
    
    public void orderItem(TraceId traceId, String itemId) {
    	TraceStatus status = null;
    	try {
    		status = trace.beginSync(traceId, "OrderServiceV2.orderItem()");

    		orderRepository.save(status.getTraceId(), itemId);
    		
    		trace.end(status);
    	} catch(Exception e) {
    		// 예외처리 로그도 남겨야 하므로 코드가 더러워진다.
    		trace.end(status, e);
    		throw e;	// 여기서 예외를 먹어버려, 정상흐름으로 동작하므로 예외를 다시 던져줘야한다. 
    	}
    }
}
