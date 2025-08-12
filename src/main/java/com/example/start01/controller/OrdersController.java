package com.example.start01.controller;


import com.example.start01.dao.OrdersDao;
import com.example.start01.dto.OrdersDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    // 내 주문 목록 (파라미터 : userId 만 있을때)
    @GetMapping("/getOrderList")
    public ArrayList<OrdersDto> getOrderList(@RequestParam Integer userId) {
        System.out.println("userId: " + userId);
        ArrayList<OrdersDto> dtos = ordersService.selectByUserId(userId);
        System.out.println("dots.size(): " + dtos.size());
        dtos.forEach(System.out::println);
        return dtos;
    }
    //페이지네이션 (파라미터 : userId, page, size)
    @GetMapping(value = "/getOrderList", params = {"page", "size"})
    public Map<String, Object> getOrderListPaging(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ordersService.selectByUserIdPaging(userId, page, size);
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
        int orderId = ordersService.insertOrder(dto);
        return orderId;
    }

    //삭제
    @DeleteMapping("/delete/{orderId}")
    public void delete(@PathVariable Integer orderId) {
        System.out.println("삭제 요청: " + orderId);
        ordersService.deleteByOrderId(orderId);

    }

//    //작성 가능한 리뷰 주문
//    @GetMapping("/canWrite")
//    public Map<String, Object> getCanReviewList(
//            @RequestParam int userId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        int offset = page * size;
//        return ordersService.getCanReviewList(userId, offset, size);
//    }

    // 공구완료 페이지용: roomId로 전체 주문 조회
    @Autowired
    private OrdersDao ordersDao;

    @GetMapping("/getOrderListByRoom")
    public List<OrdersDto> getOrderListByRoom(@RequestParam("roomId") Integer roomId) {
        return ordersDao.selectOrdersByRoomId(roomId);
    }

    @GetMapping("/orderList")
    public List<OrdersDto> orderList() {
        return ordersDao.orderList();
    }

    @GetMapping("/orderSearch")
    public List<OrdersDto> orderSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return ordersDao.orderSearch(param);
    }

    @GetMapping("/ordersCount")
    public int ordersCount() {
        return ordersDao.ordersCount();
    }

    @GetMapping("/orderTodayCount")
    public int orderTodayCount() {
        LocalDateTime startDay = LocalDate.now().atStartOfDay();
        LocalDateTime endDay = LocalDate.now().atTime(23, 59, 59);

        return ordersDao.ordersTodayCount(startDay, endDay);
    }


}

