package com.example.start01.dao;

import com.example.start01.dto.OrdersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;


@Mapper
public interface OrdersDao {
    //내 주문 목록(로그인 id 기준)
    ArrayList<OrdersDto> selectByUserId(@Param("userId") Integer userId);

    //주문 상세 (주문 pk)
    OrdersDto selectByOrderId(@Param("orderId") Integer orderId);

    // 생성
    void insertOrder(OrdersDto ordersDto);

    // 삭제
    void deleteByOrderId(@Param("orderId") Integer orderId);

    // roomId로 전체 주문 가져오기 (공구완료용)
    ArrayList<OrdersDto> selectOrdersByRoomId(@Param("roomId") Integer roomId);



}
