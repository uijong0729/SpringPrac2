package com.example.demo.v0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
    private final OrderRepositoryV0 orderRepository;
    
    @Autowired
    public OrderServiceV0(OrderRepositoryV0 orderRepository) {
		// TODO Auto-generated constructor stub
    	this.orderRepository = orderRepository; 
	}
    
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
