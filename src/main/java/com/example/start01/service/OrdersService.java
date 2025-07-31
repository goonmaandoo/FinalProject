package com.example.start01.service;

import com.example.start01.dao.OrdersDao;
import com.example.start01.dto.OrdersDto;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersDao ordersDao;

    public OrdersService(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    public OrdersDto getOrderDetail(int orderId) {
        return ordersDao.selectOrderById(orderId);
    }
}
