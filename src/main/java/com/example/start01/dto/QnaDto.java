package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class QnaDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String qContents;
    private String qAnswer;
    private LocalDateTime createdAt;
}
