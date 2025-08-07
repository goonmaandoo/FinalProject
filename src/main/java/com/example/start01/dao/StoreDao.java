package com.example.start01.dao;

import com.example.start01.dto.StoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreDao {
    List<StoreDto> StoreAll();

    List<StoreDto> StoreById(@Param("menuCategoryId") int menuCategoryId);

    StoreDto StoreDetail(int id);

    // 룸/챗
    List<StoreDto> selectStore(StoreDto storeDto);
}
