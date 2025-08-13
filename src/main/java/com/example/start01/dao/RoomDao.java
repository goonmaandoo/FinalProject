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

    RoomDto SelectByIdRoom(@Param("id") int id);

    List<RoomDto> SelectByIdOnly(@Param("storeId") int id);

    List<RoomDto> SelectByKeyword(@Param("keyword") String keyword);

    int JoinIngCount();

    int IngCount();

    int EndCount();

    int TotalCount();

    List<RoomDto> selectRoom(RoomDto roomDto);

    List<RoomDto> selectAllRoom(RoomDto roomDto);

    void updateRoomUsers(RoomDto roomDto);

    void updateKickId(RoomDto roomDto);

    // 카운팅
    void updateReadyCount(@Param("roomId") Integer roomId, @Param("delta") Integer delta);
    // 준비인원
    Integer selectReadyPeople(@Param("roomId") Integer roomId);


    List<RoomDto> AdminSelectRoom();

    List<UsersDto> selectUsersByRoomId(int roomId);


    // 공구방 상태 업데이트
    void updateRoomStatus(@Param("roomId") Integer roomId, @Param("status") String status);

    void blowUpRoom(@Param("roomId") Integer roomId);

}