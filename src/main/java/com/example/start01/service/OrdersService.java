package com.example.start01.service;

import com.example.start01.dao.OrdersDao;
import com.example.start01.dto.OrdersDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrdersService {


    @Autowired
    private OrdersDao ordersDao;


    // insert
    public int insertOrder(OrdersDto dto) {
        ordersDao.insertOrder(dto);
        return dto.getOrderId();
    }

    // selectByUserId
    public ArrayList<OrdersDto> selectByUserId(Integer userId) {
        return ordersDao.selectByUserId(userId);
    }
    //페이지네이션
    public Map<String, Object> selectByUserIdPaging(Integer userId, int page, int size) {
        int offset = page * size + 1;
        int endRow = (page + 1) * size;
        List<OrdersDto> list = ordersDao.selectByUserIdPaging(userId, offset, endRow);
        int totalCount = ordersDao.countByUserId(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("content", list);
        result.put("totalElements", totalCount);
        return  result;
    }
    // selectByOrderId
    public OrdersDto selectByOrderId(Integer orderId) {
        return ordersDao.selectByOrderId(orderId);
    }

    // delete
    public void deleteByOrderId(Integer orderId) {
        ordersDao.deleteByOrderId(orderId);
    }

//    //작성 가능한 리뷰 주문
//    public Map<String, Object> getCanReviewList(int userId, int offset, int size) {
//        List<OrdersDto> list = ordersDao.selectCanReviewByUserId(userId, offset, size);
//        int totalCount = ordersDao.countCanReviewByUserId(userId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("content", list);
//        result.put("totalElements", totalCount);
//        return result;
//    }
}
