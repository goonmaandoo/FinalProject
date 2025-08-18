package com.example.start01.controller;


import com.example.start01.dto.CashChargeDto;
import com.example.start01.dto.PaymentDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.CashChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.start01.dao.UsersDao;
import com.example.start01.utils.JwtTokenProvider;

@RestController
@RequestMapping("/api/users/cash")
public class CashChargeController {

    @Autowired
    private CashChargeService cashChargeService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsersDao usersDao;

    //캐쉬 충전
        @PostMapping("/charge")
        public void chargeCash(
                @RequestBody CashChargeDto dto,
                @RequestHeader("Authorization") String authorizationHeader) {
            //Authorization 헤더에서 토큰만 추출
            String token = authorizationHeader.startsWith("Bearer ")
                    ? authorizationHeader.substring(7)
                    : authorizationHeader;
            //JwtTokenProvider에서 이메일 추출
            String email = jwtTokenProvider.getEmail(token);
            //이메일로 db에서 userId 조회
            UsersDto user = usersDao.findByEmail(email);
            if (user == null) {
                throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
            }
            Integer userId = user.getId();
            cashChargeService.chargeCash(userId, dto.getCash());
        }

    //캐쉬 조회
    @GetMapping
    public int getCash(
            @RequestHeader("Authorization") String authorizationHerder) {
        //인증 헤더에서 토큰만 추출
        String token = authorizationHerder.startsWith("Bearer ")
                ? authorizationHerder.substring(7)
                : authorizationHerder;
        //JwtTokenProvider에서 이메일 추출
        String email = jwtTokenProvider.getEmail(token);
        //이메일로 db에서 userId조회
        UsersDto user = usersDao.findByEmail(email);
        if (user == null ) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        //캐쉬 반환
        return user.getCash() != null ? user.getCash() : 0;
    }

    //결제 (캐쉬 차감)
    @PostMapping("/pay")
    public void pay(
            @RequestBody CashChargeDto dto,
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7)
                : authorizationHeader;
        String email = jwtTokenProvider.getEmail(token);

        UsersDto user = usersDao.findByEmail(email);
        if (user == null) throw new IllegalArgumentException("존재하지 않는 회원입니다.");

        Integer userId = user.getId();
        cashChargeService.payCash(userId, dto.getCash());
    }
}
