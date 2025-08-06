package com.example.start01.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Data
public class RoomDto {
    private Integer id;
    private Integer storeId;
    private String roomName;
    private String roomAddress;
    private Integer maxPeople;
    private String users;
    private Integer leaderId;
    private String status;
    private LocalDateTime createdAt;
    private String roomAddressDetail;
    private Integer joinCount;
    private Integer joinIngCount;
    private Integer ingCount;
    private Integer endCount;
    private Integer totalCount;
}
