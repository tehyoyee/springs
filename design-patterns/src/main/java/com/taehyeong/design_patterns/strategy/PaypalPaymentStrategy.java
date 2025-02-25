package com.taehyeong.design_patterns.strategy;

import org.springframework.stereotype.Component;

@Component
public class PaypalPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " with PayPal");
    }

}
