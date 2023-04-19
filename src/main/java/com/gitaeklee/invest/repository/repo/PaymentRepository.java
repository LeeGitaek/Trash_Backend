package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Payment;
import com.gitaeklee.invest.domain.entity.QPayment;
import com.gitaeklee.invest.domain.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    // save
    public void savePayment(Payment payment) { em.persist(payment); }

    public Payment findPaymentOne(Long id) {
        QPayment payment = QPayment.payment;

        Payment findPayment = queryFactory
                .selectFrom(payment)
                .where(payment.id.eq(id))
                .fetchOne();

        return findPayment;
    }

    public List<Payment> findAllPayment() {
        QPayment payment = QPayment.payment;

        List<Payment> result = queryFactory
                .selectFrom(payment)
                .fetch();

        return result;
    }

    public List<Payment> findPaymentByUserToken(String token) {
        QPayment payment = QPayment.payment;

        List<Payment> result = queryFactory
                .select(payment)
                .from(payment)
                .where(payment.user.userToken.eq(token))
                .fetch();

        return result;
    }
}
