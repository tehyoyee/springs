package com.taehyeong.design_patterns.strategy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentStrategy paymentStrategy;

    // @Qualifier 사용 or 여러 방법으로 구현체를 선택
    public PaymentService(@Qualifier("paypalPaymentStrategy") PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void doPayment(int amount) {
        paymentStrategy.pay(amount);
    }
}