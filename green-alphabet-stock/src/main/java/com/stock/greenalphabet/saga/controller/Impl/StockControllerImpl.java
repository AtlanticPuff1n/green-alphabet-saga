package com.stock.greenalphabet.saga.controller.Impl;

import com.stock.greenalphabet.saga.controller.api.StockControllerApi;
import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StockControllerImpl implements StockControllerApi {
    private StockService stockService;

    @Override
    public List<Stock> findAll() {
        return stockService.findAll();
    }

    @Override
    public Stock addProduct(@RequestBody Stock stock) {
        return stockService.save(stock);
    }
}
