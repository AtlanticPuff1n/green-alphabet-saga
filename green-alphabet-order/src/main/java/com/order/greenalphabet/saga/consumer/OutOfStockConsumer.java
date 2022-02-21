package com.order.greenalphabet.saga.consumer;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.service.OrderEventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.order.greenalphabet.saga.constants.Constants.OUT_OF_STOCK_NAME;

@Component
public class OutOfStockConsumer {
    @Autowired
    private OrderEventService orderEventService;

    @RabbitListener(queues = OUT_OF_STOCK_NAME)
    public void consumeMessageFromQueue(Order order) {
        orderEventService.processOutOfStock(order);
    }
}