package com.example.start01.service;

import com.example.start01.dao.ChatDao;
import com.example.start01.dto.ChatDto;
import com.example.start01.dto.ChatWithUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatDao chatDao;

//    public List<ChatDto> getChatList(Integer roomId) {
//        return chatDao.getChatList(roomId);
//    }
public List<ChatWithUserDto> getChatWithUserList(Integer roomId) {
    return chatDao.selectChatWithUserList(roomId);
}
    public void inputChat(ChatDto chatDto) {
        chatDao.inputChat(chatDto);
    }
}
