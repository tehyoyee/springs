package com.taehyeong.observer_pattern;

public class SubscriberImpl2 extends Subscriber {

    public SubscriberImpl2(long id) {
        super(id);
    }

    @Override
    public void notify(String message) {
        System.out.println("[" + super.getId() + "] Event Strategy 2 Alert received :" + message);
    }

}
