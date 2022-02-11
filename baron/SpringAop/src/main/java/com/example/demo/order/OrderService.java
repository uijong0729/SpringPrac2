package com.example.demo.order;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

	private final OrderRepository repo;
	
	public OrderService(OrderRepository repo) {
		super();
		this.repo = repo;
	}

	public void orderItem(String itemId) {
		log.info("[OrderService] 실행");
		repo.save(itemId);
	}

}
