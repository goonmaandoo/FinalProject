package com.example.start01.dao;

import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersDao {
    void UsersInsert(UsersDto usersDto);

    int NicknameSelect(String nickname);

    UsersDto findByEmail(String email);

    void updateAddress(UsersDto usersDto);

    // 유저 주소 / id로 select
    UsersDto getUserAddress(Integer userId);

    // 다 불러오기
    List<UsersDto> selectAll();


    // 비밀번호 업데이트
    int updatePassword(UsersDto usersDto);

    // 비밀번호 재설정
    int resetPassword(UsersDto usersDto);

    void updateAddressAndDetail(UsersDto usersDto);

    // 회원 삭제
    int unactiveUsers(int id);

    List<UsersDto> findUsersByIds(List<Integer> userIds);

    // ⭐ user_rating 업데이트용 추가
    void updateUserRating(@Param("id") Integer id, @Param("userRating") double userRating);

    UsersDto findById(Integer id);

}



