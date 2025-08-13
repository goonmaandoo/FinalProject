package com.example.start01.service;

import com.example.start01.dao.UsersDao;
import com.example.start01.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    // 회원가입 insert
    public void UsersInsert(UsersDto usersDto) {

        usersDao.UsersInsert(usersDto);
    }

    // 닉네임 중복체크 NicknameSelect
    public boolean NicknameSelect(String nickname) {
        return usersDao.NicknameSelect(nickname) > 0; // 닉네임의 행이 1이상이면 이미 존재하는것이므로
    }

    // 로그인
    public UsersDto login(String email, String password) {
        try {
            UsersDto user = usersDao.findByEmail(email);
            if (user == null) {
                return null; // 이메일이 없을 때
            }

            if (user.getPassword().equals(password)) {
                return user;
            } else {
                return null; // 비밀번호 틀림
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 예외 발생 시 null 반환
    }

    // 주소 업데이트
    public void updateAddress(UsersDto usersDto) {
        System.out.println("usersDto:" + usersDto);
        usersDao.updateAddress(usersDto);
    }

    // 유저 주소 가져오기
    public UsersDto getUserAddress(Integer userId) {
        System.out.println("확인할 유저 아이디:" + userId);
        UsersDto dto = usersDao.getUserAddress(userId);
        System.out.println("불러온 유저 주소:" + dto);
        return dto;
    }


    // 이메일로 사용자 정보만 조회 (비번 확인 없이)
    public UsersDto findByEmail(String email) {
        return usersDao.findByEmail(email);
    }

    // 다 불러오기
    public List<UsersDto> selectAll() {
        return usersDao.selectAll();
    }

    // 비밀번호 업데이트
    public boolean updatePassword(UsersDto usersDto) {
        int result = usersDao.updatePassword(usersDto);
        return result > 0;
    }

    // 프로필
    public void updateProfile(UsersDto usersDto) {
        usersDao.updateProfile(usersDto);
    }

    ;

    public void updateProfileUrl(Integer userId, String profileUrl) {

        usersDao.updateProfileUrl(userId, profileUrl);
    }

    // 룸/챗
    public boolean updateUser(UsersDto usersDto) {
        int affectedRows = usersDao.updateUser(usersDto);
        System.out.println("usersDto:" + usersDto);
        return affectedRows > 0;  // 1 이상이면 성공
    }

    // 비밀번호 재설정
    public boolean resetPassword(UsersDto usersDto) {
        return usersDao.resetPassword(usersDto) > 0;
    }

    // 회원 삭제
    public boolean unactiveUsers(int id) {
        int result = usersDao.unactiveUsers(id);
        return result > 0;

    }
    public List<UsersDto> findUsersByIds(List<Integer> userIds) {
        return usersDao.findUsersByIds(userIds);
    }

    // ⭐ user_rating 업데이트
    public void rateUser(Integer userId, double newRating) {
        usersDao.updateUserRating(userId, newRating);
    }

    public UsersDto findById(Integer userId) {
        return usersDao.findById(userId);
    }
}

