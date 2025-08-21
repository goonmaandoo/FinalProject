package com.example.start01.dao;

import com.example.start01.dto.ReviewDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReviewAdminDao {
    //목록
    List<ReviewDto> selectAdminReviews (
            @Param("statuses") List<String> statuses, //null 또는 빈리스트 -> 전체
            @Param("startRow") int startRow,
            @Param("endRow") int endRow,
            @Param("storeId") Integer storeId,
            @Param("userId") Integer userId,
            @Param("Nickname") String Nickname,
            @Param("storeName") String StoreName,
            @Param("from") String from,
            @Param("to") String to
    );
    //전체 개수
    int countAdminReviews(
            @Param("statuses") List<String> statuses,
            @Param("storeId") Integer storeId,
            @Param("userId") Integer userId,
            @Param("from") String from,
            @Param("to") String to
    );

    // 상태별 카운트
    Map<String, Object> countByStatus(
            @Param("storeId") Integer storeId,
            @Param("userId") Integer userId,
            @Param("from") String from,
            @Param("to") String to
    );
    // 상태 변경
    int updateReviewStatus(@Param("id") int id, @Param("status") String status);
    //삭제
    int deleteReview(@Param("id") int id);

}
