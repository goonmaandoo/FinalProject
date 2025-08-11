package com.example.start01.service;

import com.example.start01.dao.CashChargeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashChargeService {
    @Autowired
    private CashChargeDao cashChargeDao;

    //캐쉬 충전
    @Transactional
    public void chargeCash(Integer userId, Integer cash) {
        if (cash == null || cash <= 1000) {
            throw new IllegalArgumentException("최소 충전 금액은 1000원 이상이어야 합니다.");
        }
        int updated = cashChargeDao.updateCash(userId, cash);
        if (updated == 0) {
            throw new RuntimeException("충전에 실패했습니다.");
        }
    }

    //현재 캐쉬 조회
    public Integer getCurrentCash(Integer userId) {
        Integer cash = cashChargeDao.selectCash(userId);
        if (cash == null) {
           throw new RuntimeException("해당 이용자를 찾을 수 없습니다.");
        }
        return cash;
    }

    //결제 (캐쉬 사용)
    @Transactional
    public void payCash(Integer userId, Integer amount) {
        if (amount == null || amount >= 0) {
            throw new IllegalArgumentException("최소 결제 금액은 0원 이상입니다.");
        }
        int updated = cashChargeDao.payCash(userId, amount);
        if(updated == 0) {
            //잔액 부족 or 사용자 없음
            throw new IllegalStateException("잔액 부족으로 결제할 수 없습니다.");
        }
    }
}
