package com.example.start01.controller;

import com.example.start01.dto.ChatReportDto;
import com.example.start01.service.ChatReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatReports")
public class ChatReportController {

    private final ChatReportService chatReportService;

    public ChatReportController(ChatReportService chatReportService) {
        this.chatReportService = chatReportService;
    }

    // 신고 생성
    @PostMapping
    public Integer insert(@RequestBody ChatReportDto dto) {
        System.out.println("신고 등록 요청: " + dto);
        return chatReportService.insert(dto);
    }

    @GetMapping
    public List<ChatReportDto> list(
            @RequestParam(required = false) Integer reportedBy,
            @RequestParam(required = false) Integer usersId
    ) {
        if (reportedBy != null) return chatReportService.getByReportedBy(reportedBy);
        if (usersId != null) return chatReportService.getByUsersId(usersId);
        //api/chatReports?reportedBy=
        //api/chatReports?usersId=

        //채팅방 기준 없으면 빈 리스트
        return List.of();
    }

    @GetMapping("/all")
    public List<ChatReportDto> getAllReports() {
        return chatReportService.selectAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        chatReportService.delete(id);
    }
}
