package com.example.start01.dao;


import com.example.start01.dto.RoomJoinDto;
import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import java.util.List;


@Mapper
public interface RoomJoinDao {

    RoomJoinDto statusCheck(@Param("roomId") Integer roomId, @Param("userId") Integer userId);


    // ✅ 새로 추가할 메서드
    List<UsersDto> getParticipantsByRoomId(@Param("roomId") Integer roomId);
    // ✅ 아래 메서드를 새로 추가
    List<UsersDto> getUsersByRoomId(@Param("roomId") Integer roomId);


    // 룸 / 챗
    List<RoomJoinDto> selectRoomJoin(RoomJoinDto roomJoinDto);

    void insertRoomJoin(RoomJoinDto roomJoinDto);


}