package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuDto {
    private Integer id;
    private Integer storeId;
    private Integer imgId;
    private String menuName;
    private Integer menuPrice;
}
