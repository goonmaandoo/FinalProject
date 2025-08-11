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



    List<RoomDto> SelectByKeyword(@Param("keyword") String keyword);

    RoomDto SelectByIdRoom(@Param("id") int id);


    // 참여자 목록 조회 (roomId 기준)
    List<UsersDto> getParticipantsByRoomId(@Param("roomId") int roomId);



}


    int JoinIngCount();

    int IngCount();

    int EndCount();

    int TotalCount();

    List<RoomDto> selectRoom(RoomDto roomDto);

    List<RoomDto> selectAllRoom(RoomDto roomDto);

    void updateRoomUsers(RoomDto roomDto);

    // 카운팅
    void updateReadyCount(@Param("roomId") Integer roomId, @Param("delta") Integer delta);
    // 준비인원
    Integer selectReadyPeople(@Param("roomId") Integer roomId);

    List<RoomDto> AdminSelectRoom();

    List<UsersDto> selectUsersByRoomId(int roomId);

}

