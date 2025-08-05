package com.example.start01.dao;

import com.example.start01.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface QnaDao {
    ArrayList<QnaDto> selectByUserId(@Param("userId") Integer userId);

    //생성
    void insertQna(QnaDto qnaDto);

    QnaDto selectByQnaId(@Param("qnaId") Integer qnaId);
    void updateQna(QnaDto qnaDto);

    void deleteByQnaId(@Param("qnaId") Integer qnaId);

    // 관리자
    ArrayList<QnaDto> selectAll();
    ArrayList<QnaDto> selectAllQna();

    ArrayList<QnaDto> selectAllAnswer();
    void updateAnswer(QnaDto qnaDto);
}
