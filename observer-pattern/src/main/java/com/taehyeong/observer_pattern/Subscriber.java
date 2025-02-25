package com.taehyeong.observer_pattern;

import lombok.Getter;

public abstract class Subscriber extends Subject {

    @Getter
    private long id;

    public Subscriber(long id) {
        this.id = id;
    }

    public void subscribe(Subscriber subscriber) {
        super.attach(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        super.detach(subscriber);
    }

    public abstract void notify(String message);

}
