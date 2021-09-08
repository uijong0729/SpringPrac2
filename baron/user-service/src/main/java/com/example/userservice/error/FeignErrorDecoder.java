package com.example.userservice.error;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component // Autowired로 Enviroment를 주입받기위해 Component로 등록
public class FeignErrorDecoder implements ErrorDecoder {

	Environment env;
	
	/**
	 *
	 */
	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		switch (response.status()) {
			case 400:
				break;
			case 404:
				if (methodKey.contains("getOrders")) {
					return new ResponseStatusException(HttpStatus.valueOf(response.status()), 
							env.getProperty("order-service.exception.order_is_empty"));
				}
				break;
			default:
				return new Exception(response.reason());
		}
		return null;
	}

}
