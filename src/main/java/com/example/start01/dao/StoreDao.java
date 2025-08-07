package com.example.start01.dao;

import com.example.start01.dto.MenuDto;
import com.example.start01.dto.StoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreDao {
    List<StoreDto> StoreAll();

    List<StoreDto> StoreUserAll();

    List<StoreDto> StoreById(@Param("menuCategoryId") int menuCategoryId);

    StoreDto StoreDetail(int id);

    List<StoreDto> SelectByKeyword(@Param("keyword") String keyword);

    void StoreInsert(StoreDto storeDto);

    int StoreCount ();

    List<StoreDto> StoreByOwnerId(@Param("ownerId") int ownerId);

    int StoreDeleteById(int id);

    List<StoreDto> storeSearch(Map<String, String> param);
}
