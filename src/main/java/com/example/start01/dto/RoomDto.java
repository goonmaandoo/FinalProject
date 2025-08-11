package com.example.start01.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
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
//    private String users;
    private Integer leaderId;
    private String status;
    private LocalDateTime createdAt;
    private String roomAddressDetail;
    private Integer joinCount;

    // 검색용 (LIKE)
    private String storeNameLike;
    // 준비인원 체크용
    private Integer readyPeople;

    private Integer joinIngCount;
    private Integer ingCount;
    private Integer endCount;
    private Integer totalCount;
    private Integer userId;
    private String roomJoinStatus;
    private String storeName;
    private List<UsersDto> usersInfo;

}
