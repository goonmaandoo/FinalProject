package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ChatDto {
    private Integer id;
    private Integer roomId;
    private String chat;
    private UUID userId;
    private LocalDateTime createdAt;
}
