package com.example.start01.dao;


import com.example.start01.dto.RoomJoinDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface RoomJoinDao {

    RoomJoinDto statusCheck(@Param("roomId") Integer roomId, @Param("userId") Integer userId);
}
