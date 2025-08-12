package com.example.start01.controller;

import com.example.start01.dto.OrdersDto;
import com.example.start01.dto.ReviewDto;
import com.example.start01.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //리뷰 작성
    @PostMapping
    public void insertReview(@RequestBody ReviewDto reviewDto) {
        System.out.println("리뷰 등록 요청 받음: " + reviewDto);
        reviewService.insertReview(reviewDto);
    }

    //특정 유저 리뷰 전체 조회 (파라미터: userId)
//    @GetMapping(value = "/getMyReview", params = {"userId"})
//    public List<ReviewDto> GetMyReview(@RequestParam Integer userId) {
//        return reviewService.getMyReview(userId);
//    }

    //페이지네이션 (파라미터: userId, page, size)
    @GetMapping(value = "/getMyReview", params = {"userId", "page", "size"})
    public Map<String, Object> getMyReview(
            @RequestParam int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return reviewService.getReviewListPaged(userId, page, size);
    }

    //리뷰 수정
    @PutMapping("/updateReview/{id}")
    public void updateReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto) {
        reviewDto.setId(id);
        reviewService.updateReview(reviewDto);
    }

    //리뷰 삭제
    @DeleteMapping("/deleteReview/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }

    //리뷰 전체 조회
    @GetMapping("/getAllReview")
    public List<ReviewDto> getAllReview() {
        return reviewService.getAllReview();
    }

//    //작성 가능한 리뷰 주문
//    @GetMapping("/canWrite")
//    public List<OrdersDto> getCanReviewList(
//            @RequestParam int userId,
//            @RequestParam(defaultValue = "0") int offset,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return reviewService.getCanReviewList(userId, offset, size);
//    }

    //작성 가능 리뷰 주문 + 페이지네이션
    @GetMapping("/canWrite")
    public Map<String, Object> getCanReviewListPaged(
            @RequestParam int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return reviewService.getCanReviewListPaged(userId, page, size);
    }

    // 사장님 리뷰 조회
    @GetMapping("/selectReviewByOwner/{ownerId}")
    public List<ReviewDto> selectReviewByOwnerId(@PathVariable int ownerId){
        return reviewService.selectReviewByOwnerId(ownerId);
    }

    // 사장님 리뷰상태 업데이트
    @PutMapping("/updateReviewByOwner/{id}")
    public void updateReviewByOwner(@PathVariable Integer id, @RequestBody ReviewDto reviewDto) {
        reviewDto.setId(id);
        reviewService.updateReviewByOwner(reviewDto);
    }
}
