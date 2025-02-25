package com.taehyeong.design_patterns.event;

import org.springframework.context.ApplicationEvent;

public class OrderCreatedClassEvent extends ApplicationEvent {

    private final Member member;

    public OrderCreatedClassEvent(Object source, Member member) {
        super(source);
        this.member = member;
    }

    public String getEventedMember() {
        return member.getName();
    }

}
