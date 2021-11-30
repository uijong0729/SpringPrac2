package app.business.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.trace.TraceStatus;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

/**
 * OrderControllerV0
 */
@RestController // = Controller + ResponseBody
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;
    
    /**
     * test url : 
     * http://localhost:8080/v3/request?itemId=1111
     * http://localhost:8080/v3/request?itemId=ex
     * 
     * @param itemId
     * @return
     */
    @GetMapping("/v3/request")
    public String request(String itemId) {
    	
    	TraceStatus status = null;
    	try {
    		status = trace.begin("OrderControllerV3.request()");
    		
    		orderService.orderItem(itemId);
    		
    		trace.end(status);
    		return "ok";
    	} catch(Exception e) {
    		// 예외처리 로그도 남겨야 하므로 코드가 더러워진다.
    		trace.end(status, e);
    		throw e;	// 여기서 예외를 먹어버려, 정상흐름으로 동작하므로 예외를 다시 던져줘야한다. 
    	}
    }

}