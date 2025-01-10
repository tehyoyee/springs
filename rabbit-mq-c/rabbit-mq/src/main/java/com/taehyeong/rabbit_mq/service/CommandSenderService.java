package com.taehyeong.rabbit_mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CommandSenderService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public CommandSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCommand(String deviceId, String command) {
        String fullCommand = deviceId + ":" + command;
        rabbitTemplate.convertAndSend(exchange, routingkey, fullCommand);
        System.out.println(" [x] Sent '" + fullCommand + "'");
    }
}