package com.taehyeong.design_patterns.controller;

import com.taehyeong.design_patterns.event.Member;
import com.taehyeong.design_patterns.event.OrderService;
import com.taehyeong.design_patterns.strategy.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @GetMapping("/event")
    public void onEventReceived() {

        orderService.createOrder(1);
        orderService.createrOrderToClass(Member.builder().name("asdf").build());

    }

    @GetMapping("/payment")
    public void onPaymentReceived(@RequestParam("amount") int amount) {

        paymentService.doPayment(amount);

    }
}
