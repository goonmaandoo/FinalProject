package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class PaymentDto {
    private Integer id;
    private UUID userId;
    private String status;
    private Integer amount;
}
