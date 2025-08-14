package com.example.start01.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReportBanDto {
    private Integer userId; //피제재자
    private Integer days; // 임시 / 영구 모두
    private String reason;
    private Integer adminId;
    private Integer reportId;

    private String status;
    private String nickname;
    private LocalDateTime bannedAt;
    private LocalDateTime bannedUntil;
    private Integer banAdminId;
    private String banReason;
}
