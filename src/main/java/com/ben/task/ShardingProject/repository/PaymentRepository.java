package com.ben.task.ShardingProject.repository;

import com.ben.task.ShardingProject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query(value = "SELECT sum(amount) FROM Payment WHERE sender_id = :sender_id")
    BigDecimal getSumBySenderId(@Param("sender_id") Integer sender_id);
}
