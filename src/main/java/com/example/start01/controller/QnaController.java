package com.example.start01.controller;

import com.example.start01.dao.QnaDao;
import com.example.start01.dto.QnaDto;
import com.example.start01.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/qna")
public class QnaController {
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

    @GetMapping("/getTheQna/{qnaId}")
    public QnaDto selectOneQna(@PathVariable Integer qnaId) {
        System.out.println("qnaId:" + qnaId);
        QnaDto dto = qnaService.selectByQnaId(qnaId);
        System.out.println("dto:" + dto);
        return dto;
    }

    @PostMapping("/insertQna")
    public void insertQna(@RequestBody QnaDto qnaDto) {
        System.out.println("Qna 등록: " + qnaDto);
        qnaService.insertQna(qnaDto);
    }

    @PutMapping("/updateQna")
    public void updateQna(@RequestBody QnaDto qnaDto) {
        System.out.println(qnaDto);
        qnaService.updateQna(qnaDto);
    }

    @DeleteMapping("/{qnaId}")
    public void deleteQna(@PathVariable Integer qnaId) {
        System.out.println("삭제 요청: " + qnaId);
        qnaService.deleteByQnaId(qnaId);
    }
    // 관리자
    @GetMapping("/selectAll")
    public ArrayList<QnaDto> selectAll() {
        ArrayList<QnaDto> dtos = qnaService.selectAll();
        return dtos;
    }

    @GetMapping("/selectAllQna")
    public ArrayList<QnaDto> selectAllQna() {
        ArrayList<QnaDto> dtos = qnaService.selectAllQna();
        System.out.println("Qna 리스트 갯수: " + dtos.size());
        return dtos;
    }

    @GetMapping("/selectAllAnswer")
    public ArrayList<QnaDto> selectAllAnswer() {
        ArrayList<QnaDto> dtos = qnaService.selectAllAnswer();
        System.out.println("Qna 리스트 갯수: " + dtos.size());
        return dtos;
    }

    @PutMapping("/updateAnswer")
    public String updateAnswer(@RequestBody QnaDto qnaDto) {
        System.out.println("answer Request");
        qnaService.updateAnswer(qnaDto);
        return "success";
    }
}
