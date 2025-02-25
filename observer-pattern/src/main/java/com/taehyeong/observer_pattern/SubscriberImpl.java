package com.taehyeong.observer_pattern;

public class SubscriberImpl extends Subscriber {

    public SubscriberImpl(long id) {
        super(id);
    }

    @Override
    public void notify(String message) {
        System.out.println("[" + super.getId() + "] Event Strategy 1 Alert received :" + message);
    }


}
