package com.example.start01.dao;

import com.example.start01.dto.ChatDto;
import com.example.start01.dto.ChatWithUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatDao {
    //List<ChatDto> getChatList(Integer roomId);
    List<ChatWithUserDto> selectChatWithUserList(@Param("roomId") Integer roomId);
    void inputChat(ChatDto chatDto);
}
