package com.example.start01.service;

import com.example.start01.dto.OrdersDto;

@Service
public class OrdersService {

    private final OrderRepository orderRepository;

    public OrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrdersDto getOrderDetail(int orderId) {
        return orderRepository.selectOrderById(orderId);
    }
}
