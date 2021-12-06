package app.business.v5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.trace.TraceStatus;
import app.trace.Template.AbstractTemplate;
import app.trace.callback.TraceTemplate;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

/**
 * OrderControllerV0
 */
@RestController // = Controller + ResponseBody
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;
    
    @Autowired
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
		super();
		this.orderService = orderService;
		this.template = new TraceTemplate(trace);
	}


	/**
     * test url : 
     * http://localhost:8080/v5/request?itemId=1111
     * http://localhost:8080/v5/request?itemId=ex
     * 
     * @param itemId
     * @return
     */
    @GetMapping("/v5/request")
    public String request(String itemId) {
    	return template.execute("OrderControllerV5.request()", () -> {
			orderService.orderItem(itemId);
			return "ok";
		});
    }

}