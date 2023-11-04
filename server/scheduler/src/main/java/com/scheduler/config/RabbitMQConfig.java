package com.scheduler.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.order.updated.name}")
    private String updatedQueueName;

    @Value("${rabbitmq.queue.order.created.name}")
    private String createdQueueName;

    @Value("${rabbitmq.queue.order.canceled.name}")
    private String canceledQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;


    @Bean
    public Queue updatedOrderQueue() {
        return new Queue(updatedQueueName, true);
    }

    @Bean
    public Queue createdOrderQueue() {
        return new Queue(createdQueueName, true);
    }

    @Bean
    public Queue canceledOrderQueue() {
        return new Queue(canceledQueueName, true);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding updatedOrderBinding(Queue updatedOrderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(updatedOrderQueue).to(exchange).with("order.updated");
    }

    @Bean
    public Binding createdOrderBinding(Queue createdOrderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(createdOrderQueue).to(exchange).with("order.created");
    }

    @Bean
    public Binding canceledOrderBinding(Queue canceledOrderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(canceledOrderQueue).to(exchange).with("order.canceled");
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper jsonMapper) {
        return new Jackson2JsonMessageConverter(jsonMapper);
    }
    
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
