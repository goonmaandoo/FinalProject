package com.example.start01.dao;

import com.example.start01.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {
    List<MenuDto> StoreMenuList(@Param("storeId") int storeId);

    List<MenuDto> StoreMenuImage(@Param("storeId") int storeId);
}
