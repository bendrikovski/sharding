package ru.yandex.task.ShardingProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.task.ShardingProject.model.Payment;
import ru.yandex.task.ShardingProject.repository.PaymentRepository;
import ru.yandex.task.ShardingProject.services.PaymentService;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments/save")
    public ResponseEntity<?> savePayments(@RequestBody List<Payment> payments, Model model) {
//        for (Payment p : payments) {
//            System.out.println(p.getAmount() + ", " + p.getSender_id() + ", " + p.getRecipient_id());
//        }
        paymentService.saveAll(payments);
        return ResponseEntity.created(URI.create("/payments/save")).build();
    }

    @PostMapping("/payments/sum")
    public BigDecimal sumPaymentsBySenderId(@RequestParam Integer sender_id, Model model) {
        BigDecimal sum = paymentService.getSumBySenderId(sender_id);
        System.out.println(sum);
        return sum;
    }

}
