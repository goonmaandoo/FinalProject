package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
public class ReviewDto {
    private Integer id;
    private Integer userId;
    private Integer score;
    private Integer storeId;
    private Integer orderId;
    private String comments;
    private java.time.LocalDateTime createdAt;
    private String roomOrder;
    private String nickname;
    private String status;
    private String storeName;
}
