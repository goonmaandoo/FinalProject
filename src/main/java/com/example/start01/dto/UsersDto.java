package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UsersDto {
    private Integer id;
    private String nickname;
    private double userRating;
    private Integer cash;
    private LocalDateTime createdAt;
    private String address;
    private String profileUrl;
    private String addressDetail;
    private String role;
    private String status;
    private String email;
    private String password;
    private String phoneNum;
    private Integer userBtnCount;
    private String joinStatus;
    private Integer roomJoinId;

}
