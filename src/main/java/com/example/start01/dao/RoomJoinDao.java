package com.example.start01.dao;


import com.example.start01.dto.RoomJoinDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Mapper
public interface RoomJoinDao {

    RoomJoinDto statusCheck(@Param("roomId") Integer roomId, @Param("userId") Integer userId);

    // 룸 / 챗
    List<RoomJoinDto> selectRoomJoin(RoomJoinDto roomJoinDto);

    void insertRoomJoin(RoomJoinDto roomJoinDto);

}
