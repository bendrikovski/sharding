package ru.yandex.task.ShardingProject.services;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.yandex.task.ShardingProject.model.Payment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    void testInsert() {
        BigDecimal error = new BigDecimal("1e-10");

        List<Payment> payments = Arrays.asList(
                new Payment(new BigDecimal(10.2d), 1, 2),
                new Payment(new BigDecimal(10.2d), 1, 2),
                new Payment(new BigDecimal(10.2d), 1, 2)
        );

        paymentService.saveAll(payments);
        payments.sort(Comparator.comparing(Payment::getId));

        List<Payment> actualPayments = paymentService.listPayments();
        actualPayments.sort(Comparator.comparing(Payment::getId));

        for (int i = 0; i < payments.size(); i++) {
            Payment p = payments.get(i);
            Payment actualP = actualPayments.get(i);
            assertEquals(p.getId(), actualP.getId());
            assertEquals(p.getRecipient_id(), actualP.getRecipient_id());
            assertEquals(p.getSender_id(), actualP.getSender_id());
            MatcherAssert.assertThat(p.getAmount(), is(closeTo(actualP.getAmount(), error)));
        }

    }

    @Test
    void aumTest() {
        List<Payment> payments = Arrays.asList(
                new Payment(new BigDecimal(10.2d), 1, 2),
                new Payment(new BigDecimal(10.2d), 1, 2),
                new Payment(new BigDecimal(10.2d), 1, 2)
        );

        paymentService.saveAll(payments);

        List<Payment> actualPayments = paymentService.listPayments();
        System.out.println("SUM");
        System.out.println(actualPayments.size());
    }

    //@..MockMVC object
    // verify

    //EntityManager
    //Datasource by Entity

    /*
    *
    * TypedQuery<Code> query = getCodeTypedQuery(requestId);
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        *
        *
        * !!!!!!!LockModeType.PESSIMISTIC_WRIT!!!!!!!!!
        *
        *
        * CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Code> criteria = builder.createQuery(Code.class);
        Root<Code> from = criteria.from(Code.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get("requestId"), requestId));

        return getEntityManager().createQuery(criteria);
    * */
}