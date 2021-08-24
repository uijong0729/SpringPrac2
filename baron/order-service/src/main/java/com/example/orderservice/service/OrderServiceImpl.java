package com.example.orderservice.service;

import com.example.orderservice.dto.*;
import com.example.orderservice.service.*;
import com.example.orderservice.jpa.*;
import com.example.orderservice.vo.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    // Autowired 생성자 주입
    OrderRepository orderRepository;
    ModelMapper mapper;

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());

        OrderEntity entity = mapper.map(orderDetails, OrderEntity.class);
        orderRepository.save(entity);

        return orderDetails;
    }

    @Override
    public Iterable<OrderEntity> getAllOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
