package com.example.start01.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDto {
    private Integer id;
    private String categoryId;
    private String storeName;
    private String storeAddress;
    private Integer minPrice;
    private String tel;
}
