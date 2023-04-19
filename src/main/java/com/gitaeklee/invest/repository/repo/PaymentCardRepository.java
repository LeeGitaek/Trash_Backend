package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.PaymentCard;
import com.gitaeklee.invest.domain.entity.QPaymentCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class PaymentCardRepository {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    // save & update card
    public void saveCard(PaymentCard card) {
        if(card.getId() == null) {
            em.persist(card);
        } else {
            em.merge(card);
        }
    }

    // search find card one
    public PaymentCard findCardOne(Long id) {
        QPaymentCard card = QPaymentCard.paymentCard;

        PaymentCard findCard = queryFactory
                .selectFrom(card)
                .where(card.id.eq(id))
                .fetchOne();

        return findCard;
    }
}
