package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatReportDto {
    private Integer id;
    private Integer chatId;
    private Integer userId;
    private String reason;
    private Integer reportedBy;
    private LocalDateTime createdAt;
}
