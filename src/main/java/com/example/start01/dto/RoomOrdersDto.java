package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomOrdersDto {
    private Integer ownerId;
    private Integer orderId;
    private String roomOrder;
    private String roomAddress;
    private String roomAddressDetail;
    private String status;
    private Integer roomId;
}
