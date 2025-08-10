package com.example.start01.controller;

import com.example.start01.dao.RoomDao;
import com.example.start01.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
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

    @GetMapping("/totalCount")
    public int totalCount() {
        return roomDao.TotalCount();
    }
    @GetMapping("/joinIngCount")
    public int joinIngCount() {
        return roomDao.JoinIngCount();
    }
    @GetMapping("/ingCount")
    public int ingCount() {
        return roomDao.IngCount();
    }
    @GetMapping("/endCount")
    public int endCount() {
        return roomDao.EndCount();
    }

    @GetMapping("/allRoomSelect")
    public List<RoomDto> AllroomSelect() {
        List<RoomDto> dto = roomDao.AllRoomSelect();
        System.out.println("dto:"+ dto);
        return dto;
    }
    @PostMapping("/create")
    public RoomDto createRoom(@RequestBody RoomDto roomDto) {
        roomDao.RoomInsert(roomDto);
        // insert 후 roomDto.id 에 자동 생성된 id가 들어감
        return roomDto;
    }

    @GetMapping("/adminSelectRoom")
    public List<RoomDto> AdminSelectRoom() {
        return roomDao.AdminSelectRoom();
    }
    @GetMapping("/adminSelectRoomUser/{id}")
    public List<RoomDto> AdminSelectRoomUser(@PathVariable("id") Integer id){
        return roomDao.AdminSelectRoomUser(id);
    }
}

