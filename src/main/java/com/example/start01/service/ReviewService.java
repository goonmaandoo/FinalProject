package com.example.start01.service;

import com.example.start01.dto.OrdersDto;
import com.example.start01.dto.ReviewDto;
import com.example.start01.dao.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    //리뷰 작성 및 중복체크
    public void insertReview(ReviewDto reviewDto) {
        int cnt = reviewDao.countByUserIdAndOrderId(reviewDto.getUserId(), reviewDto.getOrderId());
        if (cnt > 0) {
            throw new IllegalArgumentException("이미 리뷰를 작성하셨습니다.");
        }
        reviewDao.insertReview(reviewDto);
    }

    //특정 유저 리뷰 전체 조회
    public List<ReviewDto> getMyReview(Integer userId) {
        return reviewDao.selectByUserId(userId);
    }

    //페이지네이션
    public Map<String, Object> getReviewListPaged(Integer userId, int page, int size) {
        int offset = page * size;
        List<ReviewDto> list = reviewDao.selectReviewByUserIdPaging(userId, offset, size);
        int totalCount = reviewDao.countReviewByUserId(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("content", list);
        result.put("totalElements", totalCount);
        return result;
    }

//    //작성 가능한 리뷰 주문
//    public List<OrdersDto> getCanReviewList(int userId, int offset, int size) {
//        return reviewDao.selectCanReviewByUserId(userId, offset, size);
//    }

    //작성 가능한 리뷰 주문 + 페이지네이션
    public Map<String, Object> getCanReviewListPaged(int userId, int page, int size) {
        int offset = page * size;
        List<OrdersDto> list = reviewDao.selectCanReviewByUserId(userId, offset, size);
        int totalCount = reviewDao.countCanReviewByUserId(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("content", list);
        result.put("totalElements", totalCount);
        return result;
    }

    //리뷰 수정
    public void updateReview(ReviewDto reviewDto) {
        reviewDao.updateReview(reviewDto);
    }

    //리뷰 삭제
    public void deleteReview(Integer id) {
        reviewDao.deleteReview(id);
    }

    //전체 조회
    public List<ReviewDto> getAllReview() {
        return reviewDao.selectAll();
    }

    // 사장님 리뷰 조회
    public List<ReviewDto> selectReviewByOwnerId(int ownerId){
        return reviewDao.selectReviewByOwnerId(ownerId);
    }

    // 사장님 리뷰상태 업데이트
    public void updateReviewByOwner(ReviewDto reviewDto) {

        reviewDao.updateReviewByOwner(reviewDto);
    }
}
