package com.example.start01.controller;

import com.example.start01.dao.RoomDao;
import com.example.start01.dao.RoomJoinDao;
import com.example.start01.dto.RoomDto;
import com.example.start01.dto.RoomJoinDto;
import com.example.start01.dto.UsersDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.start01.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomDao roomDao;

    @Autowired
    private RoomJoinDao roomJoinDao;

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
        try {
            ObjectMapper mapper = new ObjectMapper();
            String usersJson = mapper.writeValueAsString(roomDto.getUsers()); // List<Integer> -> JSON 문자열
            roomDto.setUsers(usersJson);

            roomDao.RoomInsert(roomDto);

            Integer createdRoomId = roomDto.getId();
            Integer creatorId = roomDto.getLeaderId();

            RoomJoinDto joinDto = new RoomJoinDto();
            joinDto.setRoomId(createdRoomId);
            joinDto.setUsersId(creatorId);
            joinDto.setStatus("준비중");

            roomJoinDao.insertRoomJoin(joinDto);

            return roomDto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }



    @GetMapping("/{roomId}")
    public RoomDto getRoomById(@PathVariable("roomId") int roomId) {
        return roomDao.SelectByIdRoom(roomId);
    }


    @GetMapping("/adminSelectRoom")
    public List<RoomDto> AdminSelectRoom() {
        return roomDao.AdminSelectRoom();
    }

    @GetMapping("/getAllRoomsWithUsers")
    public List<RoomDto> getAllRoomsWithUsers() {
        List<RoomDto> rooms = roomDao.AdminSelectRoom();

        for (RoomDto room : rooms) {
            List<UsersDto> users = roomDao.selectUsersByRoomId(room.getId());
            room.setUsersInfo(users);
        }
        return rooms;
    }


}

