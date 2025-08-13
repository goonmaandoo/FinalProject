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

    //상태 전환
    int markInProgress(@Param("id") Integer id, @Param("adminId") Integer adminId);
    int resolve(@Param("id") Integer id, @Param("adminId") Integer adminId);
    int reject(@Param("id") Integer id, @Param("adminId") Integer adminId);

    //임의 상태로 바꾸기(선택)
    int updateStatus(
            @Param("id") Integer id,
            @Param("status") String status,
            @Param("adminId") Integer adminId
    );
}
