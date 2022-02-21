package com.order.greenalphabet.saga.consumer;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.service.OrderEventService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.order.greenalphabet.saga.constants.Constants.OUT_OF_STOCK_NAME;

@Component
@AllArgsConstructor
public class OutOfStockConsumer {
    private OrderEventService orderEventService;

    @RabbitListener(queues = OUT_OF_STOCK_NAME)
    public void consumeMessageFromQueue(Order order) {
        orderEventService.processOutOfStock(order);
    }
}