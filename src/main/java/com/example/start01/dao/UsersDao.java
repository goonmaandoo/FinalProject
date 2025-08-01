package com.example.start01.dao;

import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDao {
    void UsersInsert(UsersDto usersDto);

    int NicknameSelect(String nickname);

    UsersDto findByEmail(String email);

    void updateAddress(UsersDto usersDto);

    // 유저 주소 / id로 select
    UsersDto getUserAddress(Integer userId);
}
