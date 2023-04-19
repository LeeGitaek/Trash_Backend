package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Payment;
import com.gitaeklee.invest.repository.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /* find a payment */
    public Payment findOne(Long id) {
        return paymentRepository.findPaymentOne(id);
    }

    /* payment 저장 */
    @Transactional
    public Long savePayment(Payment payment) {
        validationPayment(payment);
        paymentRepository.savePayment(payment);
        return payment.getId();
    }

    /* find all payment - admin */
    public List<Payment> findAllPayment() {
        return paymentRepository.findAllPayment();
    }

    /*
     * payment 검증
     * 1. 이미 존재하는 payment 히스토리인지 검증.
     * 2. payment 에 사용된 카드가 검증된 회원의 id 에 소유된 카드인지 검증.
     * 3. price 가 비정상적인 기록이 아닌지 검증.
     * */
    private void validationPayment(Payment payment) {
        Payment paid = findOne(payment.getId());

        if(paid != null) {
            // payment 가 이미 존재하는 경우 에러를 던진다.
            throw new IllegalStateException("error: payment exist");
        }

        if(payment.getCard().getUser().getId() == null) {
            // 새로운 payment 의 카드의 유저가 불명확한 경우
            throw new IllegalStateException("error: card is not validated.");
        }

        if(payment.getPrice() <= 0) {
            // payment 의 price 가 0이거나 마이너스로 저장되는 것을 방어합니다.
            throw new IllegalStateException("error: price is negative");
        }
    }

    public List<Payment> findPaymentByUser(String userToken) {
        return paymentRepository.findPaymentByUserToken(userToken);
    }
}
