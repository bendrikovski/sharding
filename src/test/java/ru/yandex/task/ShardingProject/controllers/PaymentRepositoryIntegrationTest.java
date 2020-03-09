package ru.yandex.task.ShardingProject.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.yandex.task.ShardingProject.model.Payment;
import ru.yandex.task.ShardingProject.repository.PaymentRepository;
import ru.yandex.task.ShardingProject.ShardingProjectApplication;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingProjectApplication.class)
public class PaymentRepositoryIntegrationTest {
    private static final Payment PAYMENT1 = new Payment(new BigDecimal("10.2"), 1, 2);
    private static final Payment PAYMENT2 = new Payment(new BigDecimal("33.2"), 4, 3);

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void givenPaymentEntity_whenInsertWithSave_ThenPaymentIsPersisted() {
        paymentRepository.save(PAYMENT1);
        assertPaymentPersisted(PAYMENT1);
    }

    @Test
    public void givenPaymentEntity_whenInsertWithSaveAndFlush_ThenPaymentIsPersisted() {
        paymentRepository.save(PAYMENT2);
        assertPaymentPersisted(PAYMENT2);
    }

    private void assertPaymentPersisted(Payment input) {
        Payment payment = paymentRepository.findById(input.getId().toString()).get();
        assertNotNull(payment);
    }
}
