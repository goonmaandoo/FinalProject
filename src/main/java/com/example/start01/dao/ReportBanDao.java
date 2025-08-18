package com.example.start01.dao;

import com.example.start01.dto.ReportBanDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportBanDao {
    int banForDays(@Param("userId") Integer userId,
                   @Param("days") Integer days,
                   @Param("permanent") boolean permanent,
                   @Param("reason") String reason,
                   @Param("adminId") Integer adminId);

    //수동 해제
    int liftBan(@Param("userId") Integer userId, @Param("adminId") Integer adminId);
    
    List<ReportBanDto> selectBannedUsers();
}
