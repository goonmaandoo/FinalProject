package com.example.start01.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

//    @JsonProperty("userId")
    private Integer userId;

    private String reason;

//    @JsonProperty("reportedBy")
    private Integer reportedBy;

    private LocalDateTime createdAt;
    private String responseStatus;
    private LocalDateTime respondedAt;
    private Integer adminId;
    
    private String targetNickname; //신고 대상 닉네임
    private String reporterNickname; //신고자 닉네임
    private String chatPreview;
}
