package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatWithUserDto {
    private Integer roomId;
    private Integer userId;
    private Integer chatId;
    private String nickname;
    private String profileUrl;
    private String chat;
    private LocalDateTime createdAt;
}
