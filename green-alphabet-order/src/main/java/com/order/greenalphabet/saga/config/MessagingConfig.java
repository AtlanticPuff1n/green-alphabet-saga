package com.order.greenalphabet.saga.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.order.greenalphabet.saga.constants.Constants.OUT_OF_STOCK_NAME;
import static com.order.greenalphabet.saga.constants.Constants.STOCK_UPDATED_NAME;

@Configuration
public class MessagingConfig {
    @Bean
    public Queue queueStockUpdated() {
        return new Queue(STOCK_UPDATED_NAME);
    }

    @Bean
    public Queue queueOutOfStock() {
        return new Queue(OUT_OF_STOCK_NAME);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}