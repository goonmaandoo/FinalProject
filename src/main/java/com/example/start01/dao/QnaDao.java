package com.example.start01.dao;

import com.example.start01.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface QnaDao {
    ArrayList<QnaDto> selectByUserId(@Param("userId") Integer userId);

}
