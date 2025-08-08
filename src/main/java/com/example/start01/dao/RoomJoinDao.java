package com.example.start01.dao;


import com.example.start01.dto.RoomJoinDto;
import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RoomJoinDao {

    RoomJoinDto statusCheck(@Param("roomId") Integer roomId, @Param("userId") Integer userId);
    void insertRoomJoin(RoomJoinDto dto);  // ✅ 새로 추가

    // ✅ 새로 추가할 메서드
    List<UsersDto> getParticipantsByRoomId(@Param("roomId") Integer roomId);
    // ✅ 아래 메서드를 새로 추가
    List<UsersDto> getUsersByRoomId(@Param("roomId") Integer roomId);
}
