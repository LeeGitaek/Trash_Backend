package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.Payment;
import com.gitaeklee.invest.domain.entity.PaymentCard;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
public class PaymentDto {

    private Long paymentId;
    private int price;
    private LocalDateTime datetime;
    private PaymentCard card;

    private String token;

    public PaymentDto(Payment payment) {
        this.paymentId = payment.getId();
        this.price = payment.getPrice();
        this.datetime = payment.getDatetime();
        this.card = payment.getCard();
        this.token = payment.getUser().getUserToken();
    }

}
