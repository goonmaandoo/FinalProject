package com.example.start01.dao;

import com.example.start01.dto.PaymentDto;
import com.example.start01.dto.StoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaymentDao {
    List<PaymentDto> allPayment(@Param("comments") String comments);

    void insertCash(PaymentDto paymentDto);

    void updateUserCash(PaymentDto paymentDto);

    List<PaymentDto> allPaymentInOnly();

    void updateUserOrder(PaymentDto paymentDto);

    int totalCountPayment();

    List<PaymentDto> cashSearch(Map<String, String> param);
}
