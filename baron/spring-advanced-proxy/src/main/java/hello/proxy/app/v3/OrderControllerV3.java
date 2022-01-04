package hello.proxy.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV3 {
	
	private final OrderServiceV3 service;
	
	/**
	 * http://localhost:8080/v3/request?itemId=333
	 * 
	 * @param itemId
	 * @return
	 */
	public OrderControllerV3(OrderServiceV3 service) {
		super();
		this.service = service;
	}

	@GetMapping("/v3/request")
	public String request(String itemId) {
		// TODO Auto-generated method stub
		service.orderItem(itemId);
		return "ok";
	}
	
	@GetMapping("/v3/no-log")
	public String noLog() {
		// TODO Auto-generated method stub
		return "ok";
	}

}
