package com.example.start01.dao;

import com.example.start01.dto.OrdersDto;
import com.example.start01.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface  ReviewDao {

    //리뷰 작성
    void insertReview(ReviewDto reviewDto);

    //전체 리뷰 조회
    List<ReviewDto> selectAll();

    //특정 유저의 리뷰 전체 조회
    List<ReviewDto> selectByUserId(@Param("userId") Integer userId);

    //페이지네이션
    List<ReviewDto> selectReviewByUserIdPaging(
            @Param("userId") Integer userId,
            @Param("offset") int offset,
            @Param("size") int size
    );
    int countReviewByUserId(@Param("userId") int userId);

    //중복 체크
    int countByUserIdAndOrderId(@Param("userId") int userId, @Param("orderId") int orderId);

    //작성 가능 리뷰
    List<OrdersDto> selectCanReviewByUserId(
            @Param("userId") int userId,
            @Param("offset") int offset,
            @Param("size") int size);

    int countCanReviewByUserId(@Param("userId") int userId);

    //리뷰 1개 조회 (상세 조회)
    ReviewDto selectById(@Param("id") Integer id);

    //리뷰 수정
    void updateReview(ReviewDto review);

    //리뷰 삭제
    void deleteReview(@Param("id") Integer id);

    // 사장님 리뷰 조회
    List<ReviewDto> selectReviewByOwnerId(@Param("ownerId") int ownerId);

    // 사장님 리뷰 업데이트
    void updateReviewByOwner(ReviewDto reviewDto);

    // 특정 가게(storeId)의 리뷰 전체 조회
    List<ReviewDto> findByStoreId(@Param("storeId") int storeId);

}
