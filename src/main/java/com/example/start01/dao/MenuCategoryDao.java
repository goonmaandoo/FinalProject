package com.example.start01.dao;

import com.example.start01.dto.MenuCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuCategoryDao {
    List<MenuCategoryDto> MenuCategoryAll();
}
