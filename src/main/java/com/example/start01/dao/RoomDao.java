package com.example.start01.dao;

import com.example.start01.dto.RoomDto;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {
    List<RoomDto> RoomAll();
    List<RoomDto> RoomSelectRecruit();

    @Insert("INSERT INTO room (store_id, room_name, room_address, max_people, users, leader_id, status, room_address_detail, created_at) " +
            "VALUES (#{storeId}, #{roomName}, #{roomAddress}, #{maxPeople}, #{users}, #{leaderId}, #{status}, #{roomAddressDetail}, SYSDATE)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertRoom(RoomDto roomDto);

    List<RoomDto> SelectById(@Param("storeId") int storeId);

    List<RoomDto> RoomsWithJoinCount();

    @Select("SELECT * FROM room WHERE status=#{status}")
    RoomDto RoomSelect(String status);

    List<RoomDto> AllRoomSelect();

    List<RoomDto> SelectByIdOnly(@Param("storeId") int id);

}
