package com.taehyeong.observer_pattern.controller;

import com.taehyeong.observer_pattern.Subject;
import com.taehyeong.observer_pattern.Subscriber;
import com.taehyeong.observer_pattern.SubscriberImpl;
import com.taehyeong.observer_pattern.SubscriberImpl2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {

    private static long id = 1;
    private final Subject subject;

    @PostMapping
    public void subsribe(long subjectId) {

        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            subscribers.add(new SubscriberImpl(id));
            id++;

        }
        for (int i = 0; i < 5; i++) {
            subscribers.add(new SubscriberImpl2(id));
            id++;
        }
        for(Subscriber subscriber : subscribers) {
            subject.attach(subscriber);
        }

    }


}
