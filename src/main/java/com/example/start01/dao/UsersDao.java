package com.example.start01.dao;

import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDao {
    void UsersInsert(UsersDto usersDto);

    int NicknameSelect(String nickname);

    UsersDto findByEmail(String email);
}
