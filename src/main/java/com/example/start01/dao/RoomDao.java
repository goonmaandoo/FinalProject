package com.example.start01.dao;

import com.example.start01.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {
    List<RoomDto> RoomAll();
    List<RoomDto> RoomSelectRecruit();

    int RoomInsert(RoomDto room);

    List<RoomDto> RoomsWithJoinCount();

    @Select("SELECT * FROM room WHERE status=#{status}")
    RoomDto RoomSelect(String status);

    List<RoomDto> AllRoomSelect();
}
