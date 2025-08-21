package com.example.start01.service;


import com.example.start01.dto.ReviewDto;
import lombok.Getter;
import org.springframework.stereotype.Service;
import com.example.start01.dao.ReviewAdminDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewAdminService {

    private final ReviewAdminDao dao;

    //생성자
    public ReviewAdminService(ReviewAdminDao dao) {
        this.dao = dao;
    }
    //페이지DTO
    public static class Page<T> {
        private final List<T> content;
        private final int page;
        private final int size;
        private final int total;

        public Page(List<T> content, int page, int size, int total) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.total = total;
        }
        public List<T> getContent() { return content; }
        public int getPage() { return page; }
        public int getSize() { return size; }
        public int getTotal() { return total; }
    }

    //리스트 + 페이징
    public Page<ReviewDto> getReviews(
            List<String> statuses, Integer storeId, Integer userId, String Nickname, String storeName, String from, String to, int page, int size
    ) {
        int p = Math.max(page, 1);
        int s = Math.max(size, 10);
        int startRow = (p - 1) * s + 1;
        int endRow = p * s;

        List<String> sts = (statuses == null || statuses.isEmpty()) ? null : statuses;

        List<ReviewDto> rows = dao.selectAdminReviews(sts, startRow, endRow, storeId, userId, Nickname, storeName, from, to);
        int total = dao.countAdminReviews(sts, storeId, userId, from, to);
        return new Page<>(rows, p, s, total);
    }

    //상태별 카운트
    public Map<String, Integer> getCountByStatus(Integer storeId, Integer userId, String from, String to) {
        Map<String, Object> raw = dao.countByStatus(storeId, userId, from, to);

        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("TOTAL",    ((java.math.BigDecimal) raw.get("TOTAL")).intValue());
        counts.put("공개",     ((java.math.BigDecimal) raw.get("공개")).intValue());
        counts.put("비공개",   ((java.math.BigDecimal) raw.get("비공개")).intValue());
        counts.put("임시제한", ((java.math.BigDecimal) raw.get("임시제한")).intValue());

        return counts;
    }

    //상태 변경
    @Transactional
    public void changeStatus(int reviewId, String status) {
        if (!List.of("공개","비공개","임시제한").contains(status)) {
            throw new IllegalArgumentException("잘못된 상태: " + status);
        }
        dao.updateReviewStatus(reviewId, status);
    }

    //삭제
    @Transactional
    public void delete(int reviewId) {
        dao.deleteReview(reviewId);
    }
}
