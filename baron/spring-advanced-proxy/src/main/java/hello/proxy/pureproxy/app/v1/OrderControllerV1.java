package hello.proxy.pureproxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping		// 스프링은 @Controller 또는 @RequestMapping 어노테이션이 있어야 스프링 컨트롤러로 인식
@ResponseBody		// rest
public interface OrderControllerV1 {
	
	/**
	 * http://localhost:8080/v1/request?itemId=333
	 * 
	 * @param itemId
	 * @return
	 */
	@GetMapping("/v1/request")
	String request(@RequestParam("itemId") String itemId);
	
	@GetMapping("/v1/no-log")
	String noLog();
}
