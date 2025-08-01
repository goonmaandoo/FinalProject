package com.example.start01.dao;

import com.example.start01.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageDao {
    List<ImageDto> ImageByFolder(@Param("folder") String folder);
}
