package com.stock.greenalphabet.saga.consumer;

import com.stock.greenalphabet.saga.model.OrderDTO;
import com.stock.greenalphabet.saga.service.StockEventService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.stock.greenalphabet.saga.constants.Constants.ORDER_CREATED_NAME;

@Component
@AllArgsConstructor
public class OrderCreatedConsumer {
    private StockEventService stockEventService;

    @RabbitListener(queues = ORDER_CREATED_NAME)
    public void consumeMessageFromQueue(OrderDTO orderDTO) {
        stockEventService.process(orderDTO);
    }
}
