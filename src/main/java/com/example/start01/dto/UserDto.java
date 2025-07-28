package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserDto {
    private UUID id;
    private String nickname;
    private double userRating;
    private Integer cash;
    private LocalDateTime createdAt;
    private String address;
    private String profileUrl;
    private String roomAddressDetail;
    private String role;
    private String status;
    private String email;
    private String password;
    private String phoneNum;
}
