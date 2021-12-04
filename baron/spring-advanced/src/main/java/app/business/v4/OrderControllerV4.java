package app.business.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.trace.TraceStatus;
import app.trace.Template.AbstractTemplate;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

/**
 * OrderControllerV0
 */
@RestController // = Controller + ResponseBody
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;
    
    /**
     * test url : 
     * http://localhost:8080/v4/request?itemId=1111
     * http://localhost:8080/v4/request?itemId=ex
     * 
     * @param itemId
     * @return
     */
    @GetMapping("/v4/request")
    public String request(String itemId) {
    	
    	AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
			@Override
			protected String call() {
				orderService.orderItem(itemId);
				return "ok";
			}
		};
    	
		return template.execute("OrderControllerV4.request()");
    }

}