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

    @GetMapping("/storeid/{storeId}")
    public List<RoomDto> SelectById(@PathVariable("storeId") int storeId){
        return roomDao.SelectById(storeId);
    }
    @GetMapping("/keyword/{keyword}")
    public List<RoomDto> SelectByKeyword(@PathVariable("keyword") String keyword){
        return roomDao.SelectByKeyword(keyword);
    }

    @GetMapping("/allWithCount")
    public List<RoomDto> RoomsWithJoinCount() {
        return roomDao.RoomsWithJoinCount();
    }

    @GetMapping("/allRoomSelect")
    public List<RoomDto> AllroomSelect() {
        List<RoomDto> dto = roomDao.AllRoomSelect();
        System.out.println("dto:"+ dto);
        return dto;
    }
}
