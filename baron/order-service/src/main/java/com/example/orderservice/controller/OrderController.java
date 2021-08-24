package com.example.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.orderservice.dto.*;
import com.example.orderservice.service.*;
import com.example.orderservice.jpa.*;
import com.example.orderservice.vo.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
@Slf4j
public class OrderController {
    Environment env;
    OrderService orderService;
    ModelMapper modelMapper;

    @Autowired
    public OrderController(Environment env, OrderService orderService, ModelMapper modelMapper) {
        this.env = env;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/health_check")
    public String status() {
        log.info("check status");
        return String.format("It's working in order-com.example.orderservice.service and port : %s", env.getProperty("local.server.port"));
    }

    /**
     * http://127.0.0.1:0/order-service/{userId}/orders/
     *
     * @return
     */
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(
            @PathVariable("userId") String userId,
            @RequestBody RequestOrder orderDetails) {
        OrderDto dto = modelMapper.map(orderDetails, OrderDto.class);
        dto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(dto);
        ResponseOrder responseOrder = modelMapper.map(createdOrder, ResponseOrder.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        Iterable<OrderEntity> orders = orderService.getAllOrders(userId);

        List<ResponseOrder> responseOrders = new ArrayList<>();
        orders.forEach(orderEntity -> {
            responseOrders.add(modelMapper.map(orderEntity, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseOrders);
    }
}
