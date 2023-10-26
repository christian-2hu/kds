package com.kdsAPI.messaging.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class MessageProducer<T> {
    @Value("${rabbitmq.exchange.name}")
    protected String exchange;

    @Value("${rabbitmq.routing-key}")
    protected String routingKey;

    protected static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);
    protected final RabbitTemplate rabbitTemplate;

    public void sendMessage(T message) {
        LOGGER.info(String.format("Message sent -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}