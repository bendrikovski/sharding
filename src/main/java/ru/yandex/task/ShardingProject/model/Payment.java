package ru.yandex.task.ShardingProject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal amount;
    private Integer sender_id;
    private Integer recipient_id;

    Payment() {
    }

    public Payment(BigDecimal amount, Integer sender_id, Integer recipient_id) {
        this.amount = amount;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public Integer getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(Integer recipient_id) {
        this.recipient_id = recipient_id;
    }

    public UUID getId() {
        return id;
    }
}
