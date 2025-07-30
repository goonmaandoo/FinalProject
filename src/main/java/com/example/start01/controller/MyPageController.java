package com.example.start01.controller;

import com.example.start01.dto.QnaDto;
import com.example.start01.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/qna")
public class MyPageController {
    @Autowired
    private QnaService qnaService;

    @GetMapping("/getQnaList")
    public ArrayList<QnaDto> selectMyQna(@RequestParam Integer userId) {
        System.out.println("userId: " + userId);
        ArrayList<QnaDto> dtos = qnaService.selectByUserId(userId);
        System.out.println("dtos.size(): " + dtos.size());
        dtos.forEach(System.out::println);
        return dtos;
    }
    @DeleteMapping("/{qnaId}")
    public void deleteQna(@PathVariable Integer qnaId) {
        System.out.println("삭제 요청: " + qnaId);
        qnaService.deleteByQnaId(qnaId);
    }
}
