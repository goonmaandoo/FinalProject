package com.example.start01.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrdersDto {
    private Integer orderId;
    private Integer roomId;
    private Integer userId;
    private Integer storeId;
    private JsonNode roomOrder;
    private Integer totalPrice;
    private LocalDateTime createdAt;
}

