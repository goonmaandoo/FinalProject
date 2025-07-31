package com.example.start01.dao;

import com.example.start01.dto.OrdersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrdersDao {
    OrdersDto selectOrderById(@Param("orderId") int orderId);
}