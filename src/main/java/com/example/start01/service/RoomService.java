package com.example.start01.service;


import com.example.start01.dao.RoomDao;
import com.example.start01.dto.RoomDto;
import com.example.start01.dto.RoomOrdersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomDao roomDao;

    public List<RoomDto> selectRoom(RoomDto roomDto) {
        return roomDao.selectRoom(roomDto);

    }
    public List<RoomDto> selectAllRoom(RoomDto roomDto) {
        return roomDao.selectAllRoom(roomDto);
    }

    public void updateRoomUsers(RoomDto roomDto) {
        roomDao.updateRoomUsers(roomDto);
    }
    public void readyCount(Integer roomId, Integer delta) {
        roomDao.updateReadyCount(roomId, delta);
    }
    public Integer getReadyPeople(Integer roomId) {
        return roomDao.selectReadyPeople(roomId);
    }

    public List<RoomOrdersDto> ownerDeliverySelect(String ownerId){
        return roomDao.ownerDeliverySelect(ownerId);
    }

    public boolean ownerDeliveryUpdate(RoomOrdersDto roomOrdersDto) {
        int updatedRows = roomDao.ownerDeliveryUpdate(roomOrdersDto);
        return updatedRows > 0;
      
    public void updateRoomStatus(Integer roomId, String status) {
        roomDao.updateRoomStatus(roomId, status);
    }
    public void blowUpRoom(Integer roomId) {
        roomDao.blowUpRoom(roomId);

    }
}
