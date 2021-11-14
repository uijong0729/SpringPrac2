package app.business.v0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderControllerV0
 */
@RestController // = Controller + ResponseBody
public class OrderControllerV0 {

    private final OrderServiceV0 orderService;

    @Autowired
    public OrderControllerV0(OrderServiceV0 orderService) {
		// TODO Auto-generated constructor stub
    	this.orderService = orderService;
	}
    
    @GetMapping("/v0/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

}