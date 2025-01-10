package com.taehyeong.rabbit_mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 큐 이름
    public static final String QUEUE_NAME = "myQueue";

    // 교환기 이름
    public static final String EXCHANGE_NAME = "myExchange";

    // 라우팅 키
    public static final String ROUTING_KEY = "myRoutingKey";

    // 큐 생성
    @Bean
    public Queue myQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    // 교환기 생성
    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // 큐와 교환기 바인딩
    @Bean
    public Binding binding(Queue myQueue, DirectExchange myExchange) {
        return BindingBuilder.bind(myQueue).to(myExchange).with(ROUTING_KEY);
    }
}
