package app.business.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.trace.TraceStatus;
import app.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

/**
 * OrderControllerV0
 */
@RestController // = Controller + ResponseBody
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;
    
    /**
     * test url : http://localhost:8080/v2/request?itemId=1111
     * 
     * @param itemId
     * @return
     */
    @GetMapping("/v2/request")
    public String request(String itemId) {
    	
    	TraceStatus status = null;
    	try {
    		status = trace.begin("OrderControllerV2.request()");
    		
    		orderService.orderItem(status.getTraceId(), itemId);
    		
    		trace.end(status);
    		return "ok";
    	} catch(Exception e) {
    		// 예외처리 로그도 남겨야 하므로 코드가 더러워진다.
    		trace.end(status, e);
    		throw e;	// 여기서 예외를 먹어버려, 정상흐름으로 동작하므로 예외를 다시 던져줘야한다. 
    	}
    }

}