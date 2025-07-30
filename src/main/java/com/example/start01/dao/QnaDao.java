package com.example.start01.dao;

import com.example.start01.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QnaDao {
    public ArrayList<QnaDto> selectByUserId(Integer userId);
    //insert 상욱님
    public void updateById(QnaDto qnaDto);
    public void deleteById(Integer id);

}
