package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.PaymentCard;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.repo.PaymentCardRepository;
import com.gitaeklee.invest.repository.repo.UserRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentCardService {

    private final PaymentCardRepository paymentCardRepository;
    private final UserRepository userRepository;

    /* save card */
    public Long saveCard(PaymentCard card) {
        validationCard(card);
        paymentCardRepository.saveCard(card);
        return card.getId();
    }

    /* validation card */
    private void validationCard(PaymentCard card) {
        // 카드 번호가 16자리 인지 검증합니다.
        if(card.getCardNumber().length() != 16) {
            throw new IllegalStateException("Error : card number is not validated");
        }

        // 카드 소유주가 실제 유저로 존재하는지 여부를 검증합니다.
        User validUser = userRepository.findUserByName(card.getUser().getUserName());
        if(validUser == null) {
            throw new IllegalStateException("Error : card owner is not validated user.");
        }

        // 카드 유효기간 date 포맷을 검증합니다.
        String pattern = "^(0[1-9]|1[0-2])/[0-9]{2}$";
        Pattern patternCompiler = Pattern.compile(pattern);
        Matcher matcher = patternCompiler.matcher(card.getExpireDate());

        if(!matcher.find()) {
            throw new IllegalStateException("Error: card expire date format is not validated");
        }
    }

    public PaymentCard findCard(Long id) {
        return paymentCardRepository.findCardOne(id);
    }

    /* update card number */
    @Transactional
    public void updateCardNumber(Long id, String cardNumber) {
        PaymentCard card = findCard(id);
        card.setCardNumber(cardNumber);
    }

    /* update card expire date */
    @Transactional
    public void updateCardExpireDate(Long id, String cardExpireDate) {
        PaymentCard card = findCard(id);
        card.setExpireDate(cardExpireDate);
    }
}
