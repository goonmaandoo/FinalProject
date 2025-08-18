package com.example.start01.controller;

import com.example.start01.dao.ReviewDao;
import com.example.start01.dao.StoreDao;
import com.example.start01.dto.ReviewDto;
import com.example.start01.dto.RoomDto;
import com.example.start01.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreDao storeDao;

    @Autowired   // ✅ 이 부분 추가!
    private ReviewDao reviewDao;

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
    @GetMapping("/search")
    public List<StoreDto> storeSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return storeDao.storeSearch(param);
    }

    @PostMapping("/storeInsert")
    public String registerStore(@RequestBody StoreDto storeDto) {
        storeDao.StoreInsert(storeDto);
        return "가게 등록 완료!";
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

    @PutMapping("/storeUpdate")
    public String updateStore(@RequestBody StoreDto storeDto) {
        storeDao.StoreUpdate(storeDto);
        return "가게 수정 완료!";
    }

    @GetMapping("/{storeId}/reviews")
    public List<ReviewDto> getReviewsByStoreId(@PathVariable int storeId) {
        return reviewDao.findByStoreId(storeId);
    }



}
