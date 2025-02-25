package com.taehyeong.design_patterns.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher publisher;

    public void createOrder(int orderId) {
        // 비즈니스 로직
        System.out.println("Order created: " + orderId);

        // 이벤트 발행
        publisher.publishEvent(new OrderCreatedEvent(this, orderId));
    }

    public void createrOrderToClass(Member member) {
        publisher.publishEvent(new OrderCreatedClassEvent(this, member));
    }

}
