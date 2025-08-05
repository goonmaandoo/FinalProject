package com.example.start01.service;

import com.example.start01.dao.OrdersDao;
import com.example.start01.dto.OrdersDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


@Service
public class OrdersService {


    @Autowired
    private OrdersDao ordersDao;


    // insert
    public void insertOrder(OrdersDto dto) {
        ordersDao.insertOrder(dto);
    }

    // selectByUserId
    public ArrayList<OrdersDto> selectByUserId(Integer userId) {
        return ordersDao.selectByUserId(userId);
    }

    // selectByOrderId
    public OrdersDto selectByOrderId(Integer orderId) {
        return ordersDao.selectByOrderId(orderId);
    }

    // delete
    public void deleteByOrderId(Integer orderId) {
        ordersDao.deleteByOrderId(orderId);

    }
}
