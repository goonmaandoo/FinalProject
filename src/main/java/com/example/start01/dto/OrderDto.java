package com.example.start01.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderDto {
    private Integer orderid;
    private Integer roomid;
    private UUID userid;
    private Integer storeid;
    private JsonNode roomorder;
    private Integer totalprice;
    private LocalDateTime createdat;
}

