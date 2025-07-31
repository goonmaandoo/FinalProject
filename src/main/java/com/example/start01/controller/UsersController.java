package com.example.start01.controller;

import com.example.start01.dao.UsersDao;
import com.example.start01.dto.LoginRequest;
import com.example.start01.dto.NicknameCheckDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UsersDto user = usersService.login(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
        return ResponseEntity.ok(user);
    }

}
