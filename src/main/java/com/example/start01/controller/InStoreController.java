package com.example.start01.controller;

import com.example.start01.dto.StoreDto;
import com.example.start01.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class InStoreController {
    @Autowired
    StoreService storeService;

    @PostMapping("/selectStore")
    public List<StoreDto> selectStore(@RequestBody StoreDto storeDto) {
        System.out.println("StoreDto:"+storeDto);
        return storeService.selectStore(storeDto);
    }
}
