package com.example.start01.service;

import com.example.start01.dao.UsersDao;
import com.example.start01.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersDao registerDao;

    // 회원가입 insert
    public void UsersInsert(UsersDto usersDto)
    {

        registerDao.UsersInsert(usersDto);
    }

    // 닉네임 중복체크 NicknameSelect
    public boolean NicknameSelect(String nickname){
        return registerDao.NicknameSelect(nickname) > 0; // 닉네임의 행이 1이상이면 이미 존재하는것이므로
    }

    // 로그인
    public UsersDto login(String email, String password){
        try{
            UsersDto user = registerDao.findByEmail(email);
            if(user == null){
                return null; // 이메일이 없을 때
            }

            if(user.getPassword().equals(password)){
                return user;
            } else{
                return null; // 비밀번호 틀림
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null; // 예외 발생 시 null 반환
    }
}
