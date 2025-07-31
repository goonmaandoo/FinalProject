package com.example.start01.dao;

import com.example.start01.dto.MenuCategoryDto;
import com.example.start01.dto.PopularDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopularDao {
    List<PopularDto> PopularSelect();
}
