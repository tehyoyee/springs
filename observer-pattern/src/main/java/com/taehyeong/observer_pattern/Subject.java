package com.taehyeong.observer_pattern;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Subject {

    private static List<Subscriber> subscriberList = new ArrayList<>();
    private static String news = "";


    public void attach(Subscriber subscriber) {
        subscriberList.add(subscriber);
        notifyObservers(subscriber.getId());
    }

    public void detach(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    public void setNews(String news) {
        this.news = news;
//        notifyObservers(news);
    }

    public void notifyObservers(long id) {
        for (Subscriber subscriber : subscriberList) {
            subscriber.notify("message : " + id + " has joined : " + news);
        }
    }

}
