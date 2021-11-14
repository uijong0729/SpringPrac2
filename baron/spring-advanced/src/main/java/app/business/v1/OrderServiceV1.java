package app.business.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.trace.TraceStatus;
import app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;
    
    public void orderItem(String itemId) {
    	TraceStatus status = null;
    	try {
    		status = trace.begin("OrderServiceV1.orderItem()");

    		orderRepository.save(itemId);
    		
    		trace.end(status);
    	} catch(Exception e) {
    		// 예외처리 로그도 남겨야 하므로 코드가 더러워진다.
    		trace.end(status, e);
    		throw e;	// 여기서 예외를 먹어버려, 정상흐름으로 동작하므로 예외를 다시 던져줘야한다. 
    	}
    }
}
