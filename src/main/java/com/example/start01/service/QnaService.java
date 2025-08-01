package com.example.start01.service;

import com.example.start01.dao.QnaDao;
import com.example.start01.dto.QnaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QnaService {
    @Autowired
    private QnaDao qnaDao;

    //insert
    public void insertQna(QnaDto dto) {
        qnaDao.insertQna(dto);
    }

    // selectByUserId
    public ArrayList<QnaDto> selectByUserId(Integer userId) {
        return qnaDao.selectByUserId(userId);
    }
    // selectByQnaId
    public QnaDto selectByQnaId(Integer qnaId) {
        return qnaDao.selectByQnaId(qnaId);
    }
    // updateQnaId
    public void updateQna(QnaDto qnaDto) {
        qnaDao.updateQna(qnaDto);
    }
    // deleteByQnaId
    public void deleteByQnaId(Integer qnaId) {
        qnaDao.deleteByQnaId(qnaId);
    }

    //관리자
    public ArrayList<QnaDto> selectAllQna() {
        return qnaDao.selectAllQna();
    }
    public ArrayList<QnaDto> selectAllAnswer() {
        return qnaDao.selectAllAnswer();
    }
    public void updateAnswer(QnaDto qnaDto) {
        System.out.println("QnaService: updateAnswer 메서드 호출");
        System.out.println("QnaDto 내용: " + qnaDto);
        qnaDao.updateAnswer(qnaDto);
        qnaDao.updateAnswer(qnaDto);
    }
}
