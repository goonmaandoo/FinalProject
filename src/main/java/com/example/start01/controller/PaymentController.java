package com.example.start01.controller;

import com.example.start01.dao.PaymentDao;
import com.example.start01.dto.PaymentDto;
import com.example.start01.dto.UsersDto;
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

    @GetMapping("/allPayment")
    public List<PaymentDto> allPayment(@RequestParam String comments){ return paymentDao.allPayment(comments);}

    @PostMapping("/updateCash")
    public String updateCash(@RequestParam String comments, @RequestParam Integer id){
        int rowsAffected = paymentDao.updateCash(comments, id);
        if (rowsAffected > 0) {
            return "Updated successfully";
        } else {
            return "Update failed";
        }
    }
}
