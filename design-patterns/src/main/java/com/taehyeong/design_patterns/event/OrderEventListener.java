package com.taehyeong.design_patterns.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final MemberRepository memberRepository;

    // listener 로직
    @EventListener
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("OrderEventListener: order created -> " + event.getOrderId());
        // 이메일 알림, 로그 처리 등...
    }

    @EventListener
    public void onOrderCreatedToClass(OrderCreatedClassEvent event) {
        System.out.println("OrderEventListener: order created -> " + event.getEventedMember());
    }

}