package com.example.orderservice.service;

import com.example.orderservice.dto.*;
import com.example.orderservice.jpa.*;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    Iterable<OrderEntity> getAllOrders(String userId);
}
