package com.gitaeklee.invest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="sv_payment")
public class Payment {

    @Id @GeneratedValue
    @Column(name="payment_id")
    private Long id;

    @Column(name="payment_price")
    private int price;

    @Column(name="payment_date")
    private LocalDateTime datetime;

    // payment : card = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="card_id")
    private PaymentCard card;

    // payment : user = n: 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="access_token")
    private User user;
    /* related method */
    public void setPaymentCard(PaymentCard card) {
        this.card = card;
        card.getPayments().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getPayments().add(this);
    }

    /* entity setter & creator */
}
