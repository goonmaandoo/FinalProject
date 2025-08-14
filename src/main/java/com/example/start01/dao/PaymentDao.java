package com.example.start01.dao;

import com.example.start01.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentDao {
    List<PaymentDto> allPayment();

    void insertCash(PaymentDto paymentDto);

    List<PaymentDto> allPaymentCash();
}
