package com.example.start01.controller;

import com.example.start01.dto.RoomDto;
import com.example.start01.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class InRoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/selectRoom")
    public List<RoomDto> selectRoom(@RequestBody RoomDto roomDto) {
        System.out.println("roomDTo:"+roomDto);
        return roomService.selectRoom(roomDto);
    }
    @PostMapping("/selectAllRoom")
    public List<RoomDto> selectAllRoom(@RequestBody RoomDto roomDto) {
        System.out.println("selectAll");
        return roomService.selectAllRoom(roomDto);
    }
    @PutMapping("/updateReady")
    public void updateRoomUsers(@RequestBody RoomDto roomDto) {
        System.out.println("user ready method");
        roomService.updateRoomUsers(roomDto);
    }
    @PutMapping("/{roomId}/readyCount")
    public void updateReadyCount(@PathVariable Integer roomId, @RequestParam Integer delta) {
        System.out.println("counting"+roomId);
        roomService.readyCount(roomId, delta);
    }
    @GetMapping("/{roomId}/readyStatus")
    public Integer getReadyPeople(@PathVariable("roomId") Integer roomId) {
        System.out.println("getReadyPeople");
        return roomService.getReadyPeople(roomId);
    }
}
