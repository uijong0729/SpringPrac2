package hello.proxy.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping	// @Controller를 쓰면 컴포넌트 스캔의 대상이 되므로 RequestMapping을 붙임
@ResponseBody
public class OrderControllerV2 {

	private final OrderServiceV2 service;
	
	/**
	 * http://localhost:8080/v2/request?itemId=333
	 * 
	 * @param itemId
	 * @return
	 */
	
	public OrderControllerV2(@RequestParam("itemId") OrderServiceV2 service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/v2/request")
	public String request(String itemId) {
		// TODO Auto-generated method stub
		service.orderItem(itemId);
		return "ok";
	}
	
	@GetMapping("/v2/no-log")
	public String noLog() {
		// TODO Auto-generated method stub
		return "ok";
	}

}
