package com.example.start01.controller;


import com.example.start01.dto.ReportBanDto;
import com.example.start01.service.ReportBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportBanController {
    @Autowired
    private ReportBanService reportBanService;

    // 제재등록 및 연장
    @PostMapping("/ban")
    public ResponseEntity<String> ban(@RequestBody ReportBanDto dto) {
        reportBanService.banAndResolve(dto);
        return ResponseEntity.ok("유저가 제재됐습니다.");
    }

    //관리자 수동 해제
    @PostMapping("/unban")
    public ResponseEntity<String> unban(@RequestBody ReportBanDto dto) {
        reportBanService.lift(dto);
        return ResponseEntity.ok("유저 제재가 해제됐습니다.");
    }
}
