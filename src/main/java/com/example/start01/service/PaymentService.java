package com.example.start01.service;

import com.example.start01.dao.CashChargeDao;
import com.example.start01.dao.PaymentDao;
import com.example.start01.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private CashChargeDao cashChargeDao;

    @Transactional
    public void insertCashRefund(Integer userId, Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("환불 금액은 0보다 커야 합니다.");
        }

        PaymentDto updateDto = new PaymentDto();
        updateDto.setUserId(userId);
        updateDto.setAmount(amount);
        try {
            paymentDao.updateUserCash(updateDto);
        } catch (Exception e) {
            throw new RuntimeException("캐시 업데이트 중 오류가 발생했습니다.", e);
        }

        Integer currentCash = cashChargeDao.selectCash(userId);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(userId);
        paymentDto.setComments("cash");
        paymentDto.setInout("out");
        paymentDto.setAmount(amount);
        paymentDto.setCash(currentCash);

        paymentDao.insertCashRefund(paymentDto);
    }
}
