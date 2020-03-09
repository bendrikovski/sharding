package ru.yandex.task.ShardingProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.task.ShardingProject.model.Payment;
import ru.yandex.task.ShardingProject.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Service("paymentService")
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void saveAll(List<Payment> payments) {
        paymentRepository.saveAll(payments);
    }

    public BigDecimal getSumBySenderId(Integer sender_id) {
        return paymentRepository.getSumBySenderId(sender_id);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> listPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public void deletePayment(String paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
