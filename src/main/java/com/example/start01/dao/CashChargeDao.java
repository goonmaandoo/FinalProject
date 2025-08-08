package com.example.start01.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CashChargeDao {

    //충전(증가)
    int updateCash(@Param("userId") Integer userId, @Param("amount") Integer amount);

    //현재 캐쉬 조회
    Integer selectCash(@Param("userId") Integer userId);
}
