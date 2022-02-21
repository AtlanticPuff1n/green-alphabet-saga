package com.stock.greenalphabet.saga.service;

import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StockService.class})
@ExtendWith(SpringExtension.class)
class StockServiceTest {
    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @Test
    void testFindAll() {
        ArrayList<Stock> stockList = new ArrayList<>();
        when(this.stockRepository.findAll()).thenReturn(stockList);
        List<Stock> actualFindAllResult = this.stockService.findAll();
        assertSame(stockList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.stockRepository).findAll();
    }

    @Test
    void save() {
        Stock stock = new Stock();
        stock.setId(123L);
        stock.setName("Name");
        stock.setPrice(BigDecimal.valueOf(42L));
        stock.setQuantity(1);
        when(this.stockRepository.save((Stock) any())).thenReturn(stock);

        Stock stock1 = new Stock();
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPrice(BigDecimal.valueOf(42L));
        stock1.setQuantity(1);
        Stock actualSaveResult = this.stockService.save(stock1);
        assertSame(stock, actualSaveResult);
        assertEquals("42", actualSaveResult.getPrice().toString());
        verify(this.stockRepository).save((Stock) any());
        assertTrue(this.stockService.findAll().isEmpty());
    }
}