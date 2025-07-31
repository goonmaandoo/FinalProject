package com.example.start01.controller;

import com.example.start01.dto.OrdersDto;
import com.example.start01.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable int orderId) {
        OrdersDto order = ordersService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

}

