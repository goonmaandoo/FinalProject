package com.example.start01.controller;

import com.example.start01.dao.RoomDao;
import com.example.start01.dto.RoomDto;
import com.example.start01.dto.RoomOrdersDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomDao roomDao;

    @Autowired
    private RoomService roomService;

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

    @GetMapping("/getAllRoomsWithUsers")
    public List<RoomDto> getAllRoomsWithUsers() {
        List<RoomDto> rooms = roomDao.AdminSelectRoom();

        for (RoomDto room : rooms) {
            List<UsersDto> users = roomDao.selectUsersByRoomId(room.getId());
            room.setUsersInfo(users);
        }
        return rooms;
    }

    @GetMapping("/ownerDeliverySelect")
    public  List<RoomOrdersDto> ownerDeliverySelect(@RequestParam String ownerId){
        return roomDao.ownerDeliverySelect(ownerId);
    }

    @PutMapping("/ownerDeliveryUpdate")
    public Map<String, Object> updateStatus(@RequestBody RoomOrdersDto roomOrdersDto) {
        boolean success = roomService.ownerDeliveryUpdate(roomOrdersDto);  // service 호출

        if (success) {
            return Map.of("result", "success");
        } else {
            return Map.of("result", "fail");
        }
    }
    @GetMapping("/{roomId}")
    public RoomDto getRoomById(@PathVariable("roomId") int roomId) {
        return roomDao.SelectByIdRoom(roomId);
    }

}

