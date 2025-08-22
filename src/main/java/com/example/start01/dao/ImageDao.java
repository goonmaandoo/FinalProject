package com.example.start01.dao;

import com.example.start01.dto.ImageDto;
import com.example.start01.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageDao {
    List<ImageDto> ImageByFolder(@Param("folder") String folder);

    // 사장님 메뉴 삽입
    void MenuImageInsertByOwner(@Param("dto") ImageDto imageDto);

    // 이미지 ID로 조회
    ImageDto getImageById(Integer id);
}
