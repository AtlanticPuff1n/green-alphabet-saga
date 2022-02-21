package com.order.greenalphabet.saga.consumer;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.service.OrderEventService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.order.greenalphabet.saga.constants.Constants.STOCK_UPDATED_NAME;

@Component
@AllArgsConstructor
public class StockUpdatedConsumer {
    private OrderEventService orderEventService;

    @RabbitListener(queues = STOCK_UPDATED_NAME)
    public void consumeMessageFromQueue(Order order) {
        orderEventService.processStockUpdated(order);
    }
}
