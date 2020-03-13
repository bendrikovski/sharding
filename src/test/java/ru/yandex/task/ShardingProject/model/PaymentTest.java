package ru.yandex.task.ShardingProject.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
@RunWith(SpringRunner.class)
class PaymentTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDeserialize() throws JsonProcessingException {
        BigDecimal error = new BigDecimal("1e-10");
        String json = "{ \"sender_id\": \"2\", \"recipient_id\": \"234\", \"amount\": \"500.2\" }";
        Payment payment =  objectMapper.readValue(json, Payment.class);

        assertEquals(payment.getSender_id(), 2);
        assertEquals(payment.getRecipient_id(), 234);
        MatcherAssert.assertThat(payment.getAmount(), is(closeTo(new BigDecimal("500.2"), error)));
    }
}