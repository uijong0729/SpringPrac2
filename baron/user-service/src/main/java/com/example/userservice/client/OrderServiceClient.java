package com.example.userservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.userservice.vo.ResponseOrder;


/**
 * @author kroch
 * 
 * FeignClient로 지정된 인터페이스의 각 메소드에 GetMapping 어노테이션을 붙임으로써 해당 서비스에대한 HttpRequest를 수행한다. 
 */
@FeignClient(name = "order-service")
public interface OrderServiceClient {
	
	@GetMapping("/order-service/{userId}/orders")
	List<ResponseOrder> getOrders(@PathVariable String userId);
}
