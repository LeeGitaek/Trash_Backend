package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.Payment;
import com.gitaeklee.invest.repository.dto.PaymentDto;
import com.gitaeklee.invest.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/api/v2/payment/pay")
    public PaymentResponse savePayment(@RequestBody @Validated Payment payment) {
        Long paymentId = paymentService.savePayment(payment);
        return new PaymentResponse(paymentId);
    }

    @Data
    @AllArgsConstructor
    static class PaymentResponse {
        private Long id;
    }

    @GetMapping("/api/v2/payment/{id}")
    public PaymentDto findPayment(Long paymentId) {
        Payment payment = paymentService.findOne(paymentId);
        return new PaymentDto(payment);
    }

    @PostMapping("/api/v2/payment/history")
    public List<PaymentDto> paymentHistory(String userToken) {
        List<Payment> payments = paymentService.findPaymentByUser(userToken);
        List<PaymentDto> result = payments.stream()
                .map(o -> new PaymentDto(o))
                .collect(Collectors.toList());

        return result;
    }
}
