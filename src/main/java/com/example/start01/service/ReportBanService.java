package com.example.start01.service;

import com.example.start01.dao.ChatReportDao;
import com.example.start01.dao.ReportBanDao;
import com.example.start01.dto.ReportBanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReportBanService {

    @Autowired
    private ReportBanDao reportBanDao;
    @Autowired
    private ChatReportDao chatReportDao;

    private static final int BAN_FOREVER = 365_000; // 영구밴 기준 (1000년)

    //신고 제재
    @Transactional
    public void banAndResolve (ReportBanDto dto) {
         if (dto == null) throw new IllegalArgumentException("요청이 비었습니다.");
         if (dto.getAdminId() == null) throw new IllegalArgumentException("adminId가 필요합니다.");
         if (dto.getUserId() == null) throw new IllegalArgumentException("userId가 필요합니다.");
         if (dto.getDays() == null || dto.getDays() <= 0)
             throw new IllegalArgumentException("정지 일수는 1일 이상이어야합니다.");

         boolean permanent = dto.getDays() >= BAN_FOREVER;

            reportBanDao.banForDays(
                 dto.getUserId(),
                 dto.getDays(),
                 permanent,
                 dto.getReason(),
                 dto.getAdminId()
         );

         // 신고 경로로 온 요청 시 chat_report resolve
         if(dto.getReportId() != null) {
             int updated = chatReportDao.resolve(dto.getReportId(), dto.getAdminId());
             //이미 처리됐거나 없는 신고 -> 전체 트랜잭션 롤백
             if (updated == 0) {
                 throw new IllegalArgumentException("처리 실패(이미 처리/미존재");
             }
         }
    }

    //관리자 : 수동 해제 (status = 'active', banned_until = NULL 로 복귀
    public void lift(ReportBanDto dto) {
    if (dto == null) throw new IllegalArgumentException("요청이 비었습니다.");
    if (dto.getUserId() == null) throw new IllegalArgumentException("userId가 필요합니다.");
    if (dto.getAdminId() == null) throw new IllegalArgumentException("adminId가 필요합니다.");
    reportBanDao.liftBan(dto.getUserId(), dto.getAdminId());
    }
}
