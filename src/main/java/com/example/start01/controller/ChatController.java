package com.example.start01.controller;

import com.example.start01.dto.ChatDto;
import com.example.start01.dto.ChatWithUserDto;
import com.example.start01.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room/{roomId}/chat")
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping
    public String inputChat(@PathVariable Integer roomId, @RequestBody ChatDto chatDto) {
        chatDto.setRoomId(roomId); // URL의 roomId를 DTO에 세팅
        chatService.inputChat(chatDto);
        return "success";
    }
    @GetMapping
    public List<ChatWithUserDto> getChatList(@PathVariable Integer roomId) {
        return chatService.getChatWithUserList(roomId);
    }

}
