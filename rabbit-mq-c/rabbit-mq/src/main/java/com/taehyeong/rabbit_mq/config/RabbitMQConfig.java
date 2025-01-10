package com.taehyeong.rabbit_mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue}")
    private String queue;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    // Exchange 생성
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    // Queue 생성
    @Bean
    public Queue commandQueue() {
        return new Queue(queue, true);
    }

    // Binding 생성
    @Bean
    public Binding binding(Queue commandQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(commandQueue).to(directExchange).with(routingkey);
    }
}