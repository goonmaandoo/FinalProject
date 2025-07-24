package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PopularDto {
    private Integer id;
    private String title;
    private Integer price;
    private Integer storeId;
}
