package com.example.start01.dao;

import com.example.start01.dto.ChatReportDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatReportDao {
    ChatReportDto selectById(@Param("id") Integer id);

    List<ChatReportDto> selectByReportedBy(@Param("reportedBy") Integer reportedBy);
    List<ChatReportDto> selectByUsersId(@Param("usersId") Integer usersId);

    List<ChatReportDto> selectAll();

    void insert(ChatReportDto dto);

    void deleteById(@Param("id") Integer id);
}
