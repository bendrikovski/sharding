package com.ben.task.ShardingProject.controllers;

import com.ben.task.ShardingProject.model.Payment;
import com.ben.task.ShardingProject.model.SumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ben.task.ShardingProject.services.PaymentService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @PostMapping("/payments/save")
    public ResponseEntity<String> savePayments(@RequestBody @Valid List<Payment> payments) {
        paymentService.saveAll(payments);
        return ResponseEntity.ok("valid");
    }

    @PostMapping("/payments/sum")
    public SumResponse sumPaymentsBySenderId(@RequestParam Integer sender_id)  {
        BigDecimal sum = paymentService.getSumBySenderId(sender_id);
        return new SumResponse(sum);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
