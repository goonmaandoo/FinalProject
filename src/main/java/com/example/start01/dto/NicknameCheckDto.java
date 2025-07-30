package com.example.start01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 생성자
public class NicknameCheckDto {
    private boolean duplicate;
}
