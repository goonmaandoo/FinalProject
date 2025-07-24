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
    private UUID userid;
    private Integer score;
    private Integer orderId;
    private String comment;
    private LocalDateTime createdAt;
}
