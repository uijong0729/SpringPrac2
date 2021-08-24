package com.example.orderservice.jpa;

import org.springframework.data.repository.CrudRepository;
import com.example.orderservice.dto.*;
import com.example.orderservice.service.*;
import com.example.orderservice.jpa.*;
import com.example.orderservice.vo.*;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(String orderId);
    Iterable<OrderEntity> findByUserId(String userId);
}
