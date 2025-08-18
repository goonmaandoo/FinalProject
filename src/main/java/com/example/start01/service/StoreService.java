package com.example.start01.service;

import com.example.start01.dao.StoreDao;
import com.example.start01.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreDao storedao;

    public List<StoreDto> selectStore(StoreDto storeDto) {
        return storedao.selectStore(storeDto);
    }

    public Integer selectMinPrice(Integer storeId) {
        return storedao.selectMinPrice(storeId);
    }
}

