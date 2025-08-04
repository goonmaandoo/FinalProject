package com.example.start01.controller;

import com.example.start01.dao.UsersDao;
import com.example.start01.dto.LoginRequest;
import com.example.start01.dto.NicknameCheckDto;
import com.example.start01.dto.QnaDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.UsersService;
import com.example.start01.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> UsersInsert(@RequestBody UsersDto usersDto){
        usersService.UsersInsert(usersDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 닉네임 중복 체크
    @GetMapping("/nicknameCheck")
    public ResponseEntity<NicknameCheckDto> NicknameCheck(@RequestParam String nickname) {
        boolean isDuplicate = usersService.NicknameSelect(nickname);
        return ResponseEntity.ok(new NicknameCheckDto(isDuplicate));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UsersDto user = usersService.login(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        // 토큰 생성
        String token = jwtTokenProvider.createToken(email);

        // 토큰 + 사용자 정보를 JSON 형태로 전달
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    // 주소 업데이트
    @PutMapping("/addressUpdate")
    public void updateAddress(@RequestBody UsersDto usersDto) {
        usersService.updateAddress(usersDto);
        System.out.println("---업데이트된 usersDto---:" +usersDto);
    }
    // 주소 불러오기
    @GetMapping("/getUserAddress/{userId}")
    public UsersDto getUserAddress(@PathVariable Integer userId) {
        return usersService.getUserAddress(userId);
    }


    // 토큰으로 유저 정보 조회 (이메일 기반)
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String email = jwtTokenProvider.getEmail(token);

            // 비밀번호 확인 없이 유저 정보만 조회
            UsersDto user = usersService.findByEmail(email);
            if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 없음");

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 유효하지 않음");
        }
    }

    // 다 불러오기
    @GetMapping("/selectUserinfo")
    public List<UsersDto> selectAll(){
        return usersService.selectAll();
    }



}
