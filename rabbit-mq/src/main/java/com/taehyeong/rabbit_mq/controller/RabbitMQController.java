package com.taehyeong.rabbit_mq.controller;

import com.taehyeong.rabbit_mq.config.RabbitMQConfig;
import com.taehyeong.rabbit_mq.service.RabbitMQSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    private final RabbitMQSender rabbitMQSender;

    public RabbitMQController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        String exchange = RabbitMQConfig.EXCHANGE_NAME;
        String routingKey = RabbitMQConfig.ROUTING_KEY;
        rabbitMQSender.sendMessage(exchange, routingKey, message);
//        rabbitMQSender.sendMessage(exchange, routingKey, message);
        return "Message sent to RabbitMQ: " + message;
    }
}
