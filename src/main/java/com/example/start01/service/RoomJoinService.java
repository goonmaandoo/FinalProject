package com.example.start01.service;

import com.example.start01.dao.RoomJoinDao;
import com.example.start01.dto.RoomJoinDto;
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
    //룸 / 챗
    public List<RoomJoinDto> selectRoomJoin(RoomJoinDto roomJoinDto) {
        return joinDao.selectRoomJoin(roomJoinDto);
    }
    public void insertRoomJoin(RoomJoinDto roomJoinDto) {
        joinDao.insertRoomJoin(roomJoinDto);
    }
}
