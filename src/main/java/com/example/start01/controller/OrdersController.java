package com.example.start01.controller;


import com.example.start01.dao.OrdersDao;
import com.example.start01.dto.OrdersDto;
import com.example.start01.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    // 내 주문 목록
    @GetMapping("/getOrderList")
    public ArrayList<OrdersDto> getOrderList(@RequestParam Integer userId) {
        System.out.println("userId: " + userId);
        ArrayList<OrdersDto> dtos = ordersService.selectByUserId(userId);
        System.out.println("dots.size(): " + dtos.size());
        dtos.forEach(System.out::println);
        return dtos;
    }


    // 주문 상세
    @GetMapping("/getTheOrder/{orderId}")
    public OrdersDto getTheOrder(@PathVariable Integer orderId) {
        System.out.println("orderId: "+ orderId);
        OrdersDto dto = ordersService.selectByOrderId(orderId);
        System.out.println("DB -> DTO roomOrder: " + dto.getRoomOrder());
        return dto;
    }

    // 주문 생성
    @PostMapping("/insertOrder")
    public Integer insertOrder(@RequestBody OrdersDto dto) {
        System.out.println("insert: " + dto);
        ordersService.insertOrder(dto);
        return dto.getOrderId();
    }

    //삭제
    @DeleteMapping("/delete/{orderId}")
    public void delete(@PathVariable Integer orderId) {
        System.out.println("삭제 요청: " + orderId);
        ordersService.deleteByOrderId(orderId);

    }

    // 공구완료 페이지용: roomId로 전체 주문 조회
    @Autowired
    private OrdersDao ordersDao;

    @GetMapping("/getOrderListByRoom")
    public List<OrdersDto> getOrderListByRoom(@RequestParam("roomId") Integer roomId) {
        return ordersDao.selectOrdersByRoomId(roomId);
    }


}

