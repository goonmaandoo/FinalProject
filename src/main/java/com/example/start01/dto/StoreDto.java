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

    //  리뷰 정보 추가
    private Double avgRating;   // 평균 별점
    private Integer reviewCount; // 리뷰 개수
}
