package com.example.start01.controller;

import com.example.start01.dao.StoreDao;
import com.example.start01.dto.RoomDto;
import com.example.start01.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreDao storeDao;

    @GetMapping("/categoryId/{menuCategoryId}")
    public List<StoreDto> StoreById(@PathVariable int menuCategoryId) {
        return storeDao.StoreById(menuCategoryId);
    }
    @GetMapping ("/all")
    public List<StoreDto> StoreAll() {
        return storeDao.StoreAll();
    }

    @GetMapping ("/userAll")
    public List<StoreDto> StoreUserAll() {
        return storeDao.StoreUserAll();
    }

    @GetMapping("/storeDetail/{id}")
    public StoreDto StoreDetail(@PathVariable int id) {
        return storeDao.StoreDetail(id);
    }

    @GetMapping("/keyword/{keyword}")
    public List<StoreDto> SelectByKeyword(@PathVariable("keyword") String keyword){
        return storeDao.SelectByKeyword(keyword);
    }

    @PostMapping("/storeInsert")
    public String registerStore(@RequestBody StoreDto storeDto) {
        storeDao.StoreInsert(storeDto);
        return "Store registered successfully!";
    }
    @GetMapping("/storeCount")
    public int StoreCount() {
        return storeDao.StoreCount();
    }

    @GetMapping("/storeByOwnerId/{id}")
    public List<StoreDto> StoreByOwnerId(@PathVariable("id") int ownerId) {

        return storeDao.StoreByOwnerId(ownerId);
    }

    @GetMapping("/storeDelete/{id}")
    public int StoreDeleteById(@PathVariable int id) {

        return storeDao.StoreDeleteById(id);
    }
}
