package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class RoomJoinDto {
    private Integer id;
    private Integer roomId;
    private Integer userId;
    private LocalDateTime joinedAt;
    private String status;
}
