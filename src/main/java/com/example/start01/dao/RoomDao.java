package com.example.start01.dao;

import com.example.start01.dto.RoomDto;
import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomDao {
    List<RoomDto> RoomAll();
    List<RoomDto> RoomSelectRecruit();

    // insert 메서드명과 XML id를 일치시킴
    int RoomInsert(RoomDto roomDto);

    List<RoomDto> SelectById(@Param("storeId") int storeId);

    List<RoomDto> RoomsWithJoinCount();

    RoomDto RoomSelect(String status);

    List<RoomDto> AllRoomSelect();

    RoomDto SelectByIdRoom(@Param("id") int id);  // 단일 방 조회 (id 기준)

    List<RoomDto> SelectByKeyword(@Param("keyword") String keyword);

    // 참여자 목록 조회 (roomId 기준)
    List<UsersDto> getParticipantsByRoomId(@Param("roomId") int roomId);


}


