package com.stock.greenalphabet.saga.service;

import com.stock.greenalphabet.saga.model.OrderDTO;
import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StockEventService.class})
@ExtendWith(SpringExtension.class)
class StockEventServiceTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private StockEventService stockEventService;

    @MockBean
    private StockRepository stockRepository;

    @Test
    void testProcess() throws AmqpException {
        Stock stock = new Stock();
        stock.setId(123L);
        stock.setName("Name");
        stock.setPrice(BigDecimal.valueOf(42L));
        stock.setQuantity(1);

        Stock stock1 = new Stock();
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPrice(BigDecimal.valueOf(42L));
        stock1.setQuantity(1);
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findByName((String) any())).thenReturn(stock);
        doNothing().when(this.rabbitTemplate).convertAndSend((String) any(), (Object) any());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setQuantity(1);
        this.stockEventService.process(orderDTO);
        verify(this.stockRepository).findByName((String) any());
        verify(this.stockRepository).save((Stock) any());
        verify(this.rabbitTemplate).convertAndSend((String) any(), (Object) any());
    }

    @Test
    void testProcess2() throws AmqpException {
        Stock stock = mock(Stock.class);
        when(stock.getQuantity()).thenReturn(-1);
        doNothing().when(stock).setId((Long) any());
        doNothing().when(stock).setName((String) any());
        doNothing().when(stock).setPrice((BigDecimal) any());
        doNothing().when(stock).setQuantity((Integer) any());
        stock.setId(123L);
        stock.setName("Name");
        stock.setPrice(BigDecimal.valueOf(42L));
        stock.setQuantity(1);

        Stock stock1 = new Stock();
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPrice(BigDecimal.valueOf(42L));
        stock1.setQuantity(1);
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findByName((String) any())).thenReturn(stock);
        doNothing().when(this.rabbitTemplate).convertAndSend((String) any(), (Object) any());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setQuantity(1);
        this.stockEventService.process(orderDTO);
        verify(this.stockRepository).findByName((String) any());
        verify(stock).getQuantity();
        verify(stock).setId((Long) any());
        verify(stock).setName((String) any());
        verify(stock).setPrice((BigDecimal) any());
        verify(stock).setQuantity((Integer) any());
        verify(this.rabbitTemplate).convertAndSend((String) any(), (Object) any());
    }
}