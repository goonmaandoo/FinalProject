package com.example.start01.controller;

import com.example.start01.dao.RoomDao;
import com.example.start01.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomDao roomDao;

    @GetMapping("/all")
    public List<RoomDto> RoomAll() {
        return roomDao.RoomAll();
    }

    @GetMapping("/recruit")
    public List<RoomDto> RoomSelectRecruit() {
        return roomDao.RoomSelectRecruit();
    }

//    @PostMapping("/insert")
//    public int RoomInsert(@RequestBody RoomDto room) {
//        return roomDao.RoomInsert(room);
//    }

    @GetMapping("/allWithCount")
    public List<RoomDto> RoomsWithJoinCount() {
//        System.out.println("roomDao 호출 전");
//        List<RoomDto> rooms = roomDao.RoomsWithJoinCount();
//        System.out.println("roomDao 결과: " + rooms);
//        return rooms;
        return roomDao.RoomsWithJoinCount();
    }
}
