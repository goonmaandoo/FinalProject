package com.example.start01.dao;

import com.example.start01.dto.OrdersDto;
import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Mapper
public interface OrdersDao {
    //내 주문 목록(로그인 id 기준), 페이지네이션
    ArrayList<OrdersDto> selectByUserId(@Param("userId") Integer userId);

    //페이지네이션
    List<OrdersDto> selectByUserIdPaging(
            @Param("userId") Integer userId,
            @Param("offset") int offset,
            @Param("endRow") int endRow);

    //총 갯수
    int countByUserId(@Param("userId") Integer userId);
    
    //주문 상세 (주문 pk)
    OrdersDto selectByOrderId(@Param("orderId") Integer orderId);

    // 생성
    int insertOrder(OrdersDto ordersDto);

    // 삭제
    void deleteByOrderId(@Param("orderId") Integer orderId);

    // roomId로 전체 주문 가져오기 (공구완료용)
    ArrayList<OrdersDto> selectOrdersByRoomId(@Param("roomId") Integer roomId);

    List<OrdersDto> orderList();

    List<OrdersDto> orderSearch(Map<String, String> param);
//    //작성 가능 리뷰
//    List<OrdersDto> selectCanReviewByUserId(
//            @Param("userId") int userId,
//            @Param("offset") int offset,
//            @Param("size") int size);
//
//    //중복 체크
//    int countByUserIdAndOrderId(@Param("userId") int userId, @Param("orderId") int orderId);
//
//    int countCanReviewByUserId(@Param("userId") int userId);

    int ordersCount();

    int ordersTodayCount(@Param("startDay") LocalDateTime startDay, @Param("endDay") LocalDateTime endDay);
}
