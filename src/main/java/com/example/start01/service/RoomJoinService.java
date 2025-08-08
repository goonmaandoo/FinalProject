package com.example.start01.service;

import com.example.start01.dao.RoomJoinDao;
import com.example.start01.dto.RoomJoinDto;
import com.example.start01.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomJoinService {
    @Autowired
    RoomJoinDao joinDao;

    public RoomJoinDto statusCheck(Integer userId, Integer roomId) {
        return joinDao.statusCheck(roomId, userId);
    }

    public List<UsersDto> getUsersByRoomId(Integer roomId) {
        return joinDao.getUsersByRoomId(roomId);
    }
}
