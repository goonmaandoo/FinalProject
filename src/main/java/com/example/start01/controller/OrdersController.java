package com.example.start01.controller;

import com.example.start01.dto.OrdersDto;
import com.example.start01.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService orderService;

    public OrdersController(OrdersService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable int orderId) {
        OrdersDto order = orderService.getOrderDetail(orderId);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }
}
