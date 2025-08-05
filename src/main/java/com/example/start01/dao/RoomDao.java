package com.example.start01.dao;

import com.example.start01.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {
    List<RoomDto> RoomAll();
    List<RoomDto> RoomSelectRecruit();

    int RoomInsert(RoomDto room);

    List<RoomDto> SelectById(@Param("storeId") int storeId);

    List<RoomDto> RoomsWithJoinCount();

    @Select("SELECT * FROM room WHERE status=#{status}")
    RoomDto RoomSelect(String status);

    List<RoomDto> AllRoomSelect();

    List<RoomDto> SelectByIdOnly(@Param("storeId") int id);

    List<RoomDto> SelectByKeyword(@Param("keyword") String keyword);
}
