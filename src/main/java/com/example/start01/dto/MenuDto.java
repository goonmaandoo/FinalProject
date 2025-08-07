package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuDto {
    private Integer id;
    private Integer menuCategoryId;
    private Integer storeId;
    private Integer imageId;
    private String menuName;
    private Integer menuPrice;
    private String folder;
    private String filename;

    //조건 검색
    private String menuNameLike;

    private String storeName;
    private String ownerId;

}
