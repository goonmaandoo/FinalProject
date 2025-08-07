package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDto {
    private Integer id;
    private Integer menuCategoryId;
    private String storeName;
    private String storeAddress;
    private Integer minPrice;
    private String tel;
    private Integer ownerId;
    private Integer storeCount;
    private String nickName;
}
