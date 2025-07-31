package com.example.start01.service;

import com.example.start01.dao.RegisterDao;
import com.example.start01.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private RegisterDao registerDao;

    // 회원가입 insert
    public void UsersInsert(UsersDto usersDto)
    {
        registerDao.UsersInsert(usersDto);
    }

    // 닉네임 중복체크 NicknameSelect
    public boolean NicknameSelect(String nickname){
        return registerDao.NicknameSelect(nickname) > 0; // 닉네임의 행이 1이상이면 이미 존재하는것이므로
    }
}
