package com.example.start01.service;

import com.example.start01.dto.ChatReportDto;
import com.example.start01.dao.ChatReportDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatReportService {
    //    @Autowired
    private final ChatReportDao chatReportDao;

    public ChatReportService(ChatReportDao chatReportDao) {
        this.chatReportDao = chatReportDao;
    }

    public List<ChatReportDto> getByUsersId(Integer usersId) {
        return chatReportDao.selectByUsersId(usersId);
    }

    public List<ChatReportDto> getByReportedBy(Integer reportedBy) {
        return chatReportDao.selectByReportedBy(reportedBy);
    }

    public List<ChatReportDto> selectAll() {
        return chatReportDao.selectAll();
    }

    public ChatReportDto get(Integer id) {
        return chatReportDao.selectById(id);
    }

    public Integer insert(ChatReportDto dto) {
        chatReportDao.insert(dto);
        return dto.getId();
    }

    public void delete(Integer id) {
        chatReportDao.deleteById(id);
    }


    public void markInProgress(Integer id, Integer adminId) {
        int updated = chatReportDao.markInProgress(id, adminId);
        if (updated == 0) throw new IllegalStateException("상태 변경 실패(이미 처리됨 또는 미존재).");
    }

    public void resolve(Integer id, Integer adminId) {
        int updated = chatReportDao.resolve(id, adminId);
        if (updated == 0) throw new IllegalStateException("상태 변경 실패(이미 처리됨 또는 미존재).");
    }

    public void reject(Integer id, Integer adminId) {
        int updated = chatReportDao.reject(id, adminId);
        if (updated == 0) throw new IllegalStateException("상태 변경 실패(이미 처리됨 또는 미존재).");
    }
    // (선택) 임의 상태 업데이트
    public void updateStatus(Integer id, String status, Integer adminId) {
        if (!List.of("pending","in_progress","resolved","rejected").contains(status)) {
            throw new IllegalArgumentException("허용되지 않는 상태값");
        }
        chatReportDao.updateStatus(id, status, adminId);
    }

    public List<ChatReportDto> selectAdminReports(String status, Integer userId, Integer reportedBy,
                                                  java.sql.Timestamp from, java.sql.Timestamp to,
                                                  String keyword, int offset, int size, String chatPreview) {
        return chatReportDao.selectAdminReports(status, userId, reportedBy, from, to, keyword, offset, size, chatPreview);
    }

    public int countAdminReports(String status, Integer userId, Integer reportedBy,
                                 java.sql.Timestamp from, java.sql.Timestamp to,
                                 String keyword) {
        return chatReportDao.countAdminReports(status, userId, reportedBy, from, to, keyword);
    }
}