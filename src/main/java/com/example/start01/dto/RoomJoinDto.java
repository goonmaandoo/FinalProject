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
    private Integer usersId;
    private LocalDateTime joinedAt;
    private String status;

    // users 테이블에서 JOIN해서 가져올 필드 추가
    private String nickname;
    private String profileUrl;

}
