package com.example.start01.controller;


import com.example.start01.dto.RoomJoinDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.RoomJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roomJoin")
public class RoomJoinController {
    @Autowired
    RoomJoinService joinService;
    // 참여 상태 체킹
    @GetMapping("/statusCheck")
    public RoomJoinDto statusCheck(@RequestParam Integer roomId, @RequestParam Integer userId) {
        System.out.println("조회할 roomId"+roomId);
        System.out.println("조회할 userId"+userId);
        RoomJoinDto joinDto = joinService.statusCheck(userId, roomId);
        return joinDto;

    }

    @GetMapping("/participants")
    public List<UsersDto> getParticipants(@RequestParam Integer roomId) {
        System.out.println("📥 참여자 목록 조회 - roomId: " + roomId);
        return joinService.getUsersByRoomId(roomId);  // UsersDto 리스트 반환
    }

}
