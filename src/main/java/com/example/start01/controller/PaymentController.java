package com.example.start01.controller;

import com.example.start01.dao.CashChargeDao;
import com.example.start01.dao.PaymentDao;
import com.example.start01.dao.UsersDao;
import com.example.start01.dto.PaymentDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/refund")
    public void insertCash(@RequestBody PaymentDto paymentDto){
        paymentDao.insertCash(paymentDto);
    }

    @GetMapping("/allPayment")
    public List<PaymentDto> allPayment(@RequestParam String comments){ return paymentDao.allPayment(comments);}

    @PostMapping("/insertCashRefund")
    public String insertCashRefund(@RequestParam Integer userId, @RequestParam Integer amount){
        paymentService.insertCashRefund(userId,amount);
        return "환불 처리 완료";
    }
}
