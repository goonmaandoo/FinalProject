package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class PaymentDto {
    private Integer id;
    private Integer userId;
    private String inout;
    private Integer amount;
    private String comments;
    private LocalDateTime createdAt;
    private String email;
    private String nickName;
}
