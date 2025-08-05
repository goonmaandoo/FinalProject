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

    public Integer insert(ChatReportDto dto) {
        chatReportDao.insert(dto);
        return dto.getId();
    }

    public void delete(Integer id) {
        chatReportDao.deleteById(id);
    }
}
