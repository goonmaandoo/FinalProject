package com.example.start01.controller;

import com.example.start01.dto.ChatReportDto;
import com.example.start01.service.ChatReportService;
import com.example.start01.service.ReportBanService;
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

    //관리자용 페이징 리스트
    @GetMapping("/admin")
    public java.util.Map<String, Object> adminList(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer reportedBy,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") java.time.LocalDateTime from,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") java.time.LocalDateTime to,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String chatPreview
    ) {
        int offset = page * size;
        java.sql.Timestamp fromTs = (from == null) ? null : java.sql.Timestamp.valueOf(from);
        java.sql.Timestamp toTs = (to == null) ? null : java.sql.Timestamp.valueOf(to);

        var items = chatReportService.selectAdminReports(status, userId, reportedBy, fromTs, toTs, keyword, offset, size, chatPreview);
        int total = chatReportService.countAdminReports(status, userId, reportedBy, fromTs, toTs, keyword);

        var res = new java.util.HashMap<String, Object>();
        res.put("items", items);
        res.put("total", total);
        return res;
    }



    @GetMapping("/all")
    public List<ChatReportDto> getAllReports() {
        return chatReportService.selectAll();
    }
    @GetMapping("/{id}")
    public ChatReportDto getOne(@PathVariable Integer id) {
        return chatReportService.get(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        chatReportService.delete(id);
    }

    // 상태 전환
    //처리중
    @PatchMapping("/{id}/in-progress")
    public void inProgress(@PathVariable Integer id, @RequestParam Integer adminId) {
        chatReportService.markInProgress(id, adminId);
    }
    //처리완료
    @PatchMapping("/{id}/resolve")
    public void resolve(@PathVariable Integer id, @RequestParam Integer adminId) {
        chatReportService.resolve(id, adminId);
    }
    //기각
    @PatchMapping("/{id}/reject")
    public void reject(@PathVariable Integer id, @RequestParam Integer adminId) {
        chatReportService.reject(id, adminId);
    }
    //임의 상태 업데이트(선택)
    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable Integer id,
                             @RequestParam String status,
                             @RequestParam Integer adminId) {
        chatReportService.updateStatus(id, status, adminId);
    }
}
