package com.example.start01.dao;

import com.example.start01.dto.PaymentDto;
import com.example.start01.dto.StoreDto;
import com.example.start01.dto.UsersDto;
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

    Integer totalCountPayment();

    List<PaymentDto> cashSearch(Map<String, String> param);

    List<PaymentDto> refundSearchAll(Map<String, String> param);

    List<PaymentDto> refundSearchCash(Map<String, String> param);

    List<PaymentDto> refundSearchOrder(Map<String, String> param);

    void updateStatus(@Param("id") Integer id);
}
