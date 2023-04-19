package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.Payment;
import com.gitaeklee.invest.domain.entity.PaymentCard;
import com.gitaeklee.invest.repository.dto.PaymentCardDto;
import com.gitaeklee.invest.service.PaymentCardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentCardController {

    private final PaymentCardService paymentCardService;

    @PostMapping("/api/v2/card/register")
    public PaymentCardResponse savePaymentCard(@RequestBody @Validated PaymentCard card) {
        Long cardId = paymentCardService.saveCard(card);
        return new PaymentCardResponse(cardId);
    }

    @Data
    @AllArgsConstructor
    static class PaymentCardResponse {
        private Long id;
    }

    /* update card information */
    @Data
    @AllArgsConstructor
    static class UpdateCardRequest {
        private Long cardId;
        private String cardNumber;
        private String expireDate;
    }

    @PostMapping("/api/v2/card/update")
    public PaymentCardDto updateCard(@RequestBody @Validated UpdateCardRequest request) {
        paymentCardService.updateCardNumber(request.cardId, request.cardNumber);
        paymentCardService.updateCardExpireDate(request.cardId, request.expireDate);
        PaymentCard updatedCard = paymentCardService.findCard(request.cardId);
        return new PaymentCardDto(updatedCard);
    }



}
