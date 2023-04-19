package com.gitaeklee.invest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="sv_payment_card")
public class PaymentCard {

    @Id @GeneratedValue
    @Column(name="card_id")
    private Long id;

    private String cardNumber;
    private String expireDate;
    private String cardType;

    // card: user = n: 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    // card: payment = 1 : n
    @OneToMany(mappedBy = "card")
    private List<Payment> payments = new ArrayList<>();

    /* related method */
    public void setUser(User user) {
        this.user = user;
        user.getCards().add(this);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setPaymentCard(this);
    }

    /* entity setter & creator */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
