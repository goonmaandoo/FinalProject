package com.example.start01.dao;

import com.example.start01.dto.StoreDto;
import com.example.start01.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    // 프로필
    void updateProfile(UsersDto usersDto);

    void updateProfileUrl(@Param("userId") Integer userId, @Param("profileUrl") String profileUrl);

    // 룸 챗
    int updateUser(UsersDto usersDto);

    // 회원 삭제
    int unactiveUsers(int id);

    int TotalCount();

    int unactiveCount();

    int banCount();

    int userBtnCountRole(@Param("role") String role);

    List<UsersDto> userBtnCount(@Param("role") String role);

    List<UsersDto> selectAllAdmin();

    List<UsersDto> selectAllActive();

    List<UsersDto> unactiveBan(@Param("status") String status);

    List<UsersDto> userBanSearch(Map<String, String> param);

    List<UsersDto> userUnactiveSearch(Map<String, String> param);

    List<UsersDto> userSearchActive(Map<String, String> param);

    List<UsersDto> userSearch(Map<String, String> param);

    List<UsersDto> userOwnerSearch(Map<String, String> param);

    List<UsersDto> userSearchAdmin(Map<String, String> param);

    UsersDto UserInfo(@Param("id") Integer Id);

    int updateStatusBan(@Param("status") String status, @Param("id") Integer id, @Param("LocalDateTime") LocalDateTime activeAt);

    int updateStatus(@Param("status") String status, @Param("id") Integer id);

    List<UsersDto> findUsersByIds(@Param("userIds") List<Integer> userIds);

    // ⭐ user_rating 업데이트용 추가
    void updateUserRating(@Param("id") Integer id, @Param("userRating") double userRating);

    UsersDto findById(Integer id);

}
