package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.Payment;
import com.gitaeklee.invest.domain.entity.PaymentCard;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class PaymentCardDto {

    private Long cardId;
    private String cardNumber;
    private String expireDate;
    private String cardType;

    private User user;
    private List<Payment> payments = new ArrayList<>();

    public PaymentCardDto(PaymentCard card) {
        this.cardId = card.getId();
        this.cardNumber = card.getCardNumber();
        this.expireDate = card.getExpireDate();
        this.cardType = card.getCardType();
        this.user = card.getUser();
        this.payments = card.getPayments();
    }
}
