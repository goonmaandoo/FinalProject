package com.example.start01.controller;

import com.example.start01.dao.CashChargeDao;
import com.example.start01.dao.PaymentDao;
import com.example.start01.dao.UsersDao;
import com.example.start01.dto.PaymentDto;
import com.example.start01.dto.StoreDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("/allPaymentInOnly")
    public List <PaymentDto> allPaymentInOnly(){
        return paymentDao.allPaymentInOnly();
    }

    @PostMapping("/insertCashRefund")
    public String insertCashRefund(@RequestParam Integer userId, @RequestParam Integer amount){
        paymentService.insertCashRefund(userId,amount);
        return "환불 처리 완료";
    }

    @PostMapping("/insertOrderCancel")
    public String insertOrderCancel(@RequestParam Integer userId, @RequestParam Integer amount){
        paymentService.insertOrderCancel(userId,amount);
        return "주문 취소 완료";
    }

    @GetMapping("/totalCountPayment")
    public int totalCountPayment(){
        return paymentDao.totalCountPayment();
    }

    @GetMapping("/search")
    public List<PaymentDto> cashSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return paymentDao.cashSearch(param);
    }

    @GetMapping("/refundSearchAll")
    public List<PaymentDto> refundSearchAll(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return paymentDao.refundSearchAll(param);
    }

    @GetMapping("/refundSearchCash")
    public List<PaymentDto> refundSearchCash(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return paymentDao.refundSearchCash(param);
    }

    @GetMapping("/refundSearchOrder")
    public List<PaymentDto> refundSearchOrder(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return paymentDao.refundSearchOrder(param);
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("id") Integer id) {
        paymentDao.updateStatus(id);
        return "가게 수정 완료!";
    }
}
