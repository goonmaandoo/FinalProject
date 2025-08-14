package com.example.start01.controller;

import com.example.start01.dao.PaymentDao;
import com.example.start01.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao;

    @PostMapping("/refund")
    public void insertCash(@RequestBody PaymentDto paymentDto){
        paymentDao.insertCash(paymentDto);
    }
    @GetMapping("/allPaymentCash")
    public List<PaymentDto> allPaymentCash(){
        return paymentDao.allPaymentCash();
    }
//   @GetMapping("/updateCash")
//    public void updateCash(@RequestParam String comments, @RequestParam Integer id){
//        return paymentDao.updateCash(comments, id);
//   }
}
