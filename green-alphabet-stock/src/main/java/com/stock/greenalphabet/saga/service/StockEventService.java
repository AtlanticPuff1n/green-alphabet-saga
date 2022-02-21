package com.stock.greenalphabet.saga.service;

import com.stock.greenalphabet.saga.model.OrderDTO;
import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.stock.greenalphabet.saga.constants.Constants.OUT_OF_STOCK_NAME;
import static com.stock.greenalphabet.saga.constants.Constants.STOCK_UPDATED_NAME;

@Slf4j
@Service
public class StockEventService {
    @Autowired
    private RabbitTemplate template;

    @Autowired
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