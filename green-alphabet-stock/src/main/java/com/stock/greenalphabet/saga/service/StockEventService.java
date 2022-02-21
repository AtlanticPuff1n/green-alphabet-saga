package com.stock.greenalphabet.saga.service;

import com.stock.greenalphabet.saga.model.OrderDTO;
import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.stock.greenalphabet.saga.constants.Constants.OUT_OF_STOCK_NAME;
import static com.stock.greenalphabet.saga.constants.Constants.STOCK_UPDATED_NAME;

@Service
@AllArgsConstructor
public class StockEventService {
    private RabbitTemplate template;
    private StockRepository stockRepository;

    public void process(OrderDTO orderDTO) {
        Stock stock = stockRepository.findByName(orderDTO.getName());
        if (stock.getQuantity() >= orderDTO.getQuantity()) {
            stock.setQuantity(stock.getQuantity() - orderDTO.getQuantity());
            stockRepository.save(stock);
            template.convertAndSend(STOCK_UPDATED_NAME, orderDTO);
        } else {
            template.convertAndSend(OUT_OF_STOCK_NAME, orderDTO);
        }
    }
}