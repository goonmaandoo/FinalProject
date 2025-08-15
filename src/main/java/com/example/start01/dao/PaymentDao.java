package com.example.start01.dao;

import com.example.start01.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentDao {
    List<PaymentDto> allPayment(@Param("comments") String comments);

    void insertCash(PaymentDto paymentDto);

    int updateCash(@Param("comments") String comments, @Param("id") Integer id);
}
