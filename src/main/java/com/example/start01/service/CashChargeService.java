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
            throw new IllegalArgumentException("충전 금액은 1000원 이상이어야 합니다.");
        }
        int updated = cashChargeDao.updateCash(userId, cash);
        if (updated == 0) {
            throw new RuntimeException("해당 이용자를 찾을 수 없습니다.");
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
}
