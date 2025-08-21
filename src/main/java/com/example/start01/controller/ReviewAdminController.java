package com.example.start01.controller;

import com.example.start01.dto.ReviewDto;
import com.example.start01.service.ReviewAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/reviewadmin")
public class ReviewAdminController {

    private final ReviewAdminService service;

    public ReviewAdminController(ReviewAdminService service) {
        this.service = service;
    }

    //목록
    @GetMapping
    public ReviewAdminService.Page<ReviewDto> list(
            @RequestParam(required = false) List<String> status, // /?status=공개&status=비공개
            @RequestParam(required = false) Integer storeId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String from, // "2025-08-01 00:00:00"
            @RequestParam(required = false) String to,   // "2025-08-01 00:00:00"
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getReviews(status, storeId, userId, nickname, storeName, from, to, page, size);
    }

    //상태별 카운트
    @GetMapping("/counts")
    public Map<String, Integer> counts(
            @RequestParam(required = false) Integer storeId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        return service.getCountByStatus(storeId, userId, from, to);
    }

    //상태 변경
    @PatchMapping("/{id}/status")
    public void changeStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        service.changeStatus(id, body.get("status"));
    }

    //삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
