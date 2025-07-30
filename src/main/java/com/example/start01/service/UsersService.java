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
}
