package com.example.start01.dao;

import com.example.start01.dto.RoomDto;
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


<<<<<<< HEAD
    RoomDto SelectByIdRoom(@Param("id") int id);
=======
    RoomDto SelectByIdOnly(@Param("id") int id);
>>>>>>> d12c9b045d9125bfe3cfab8ab3b7d9d7e996545c

    List<RoomDto> SelectByIdOnly(@Param("storeId") int id);

    List<RoomDto> SelectByKeyword(@Param("keyword") String keyword);

<<<<<<< HEAD
}
=======
}
>>>>>>> d12c9b045d9125bfe3cfab8ab3b7d9d7e996545c
